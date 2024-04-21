package com.quind.prueba.tecnica.domain.usecase;

import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import com.quind.prueba.tecnica.domain.model.models.Task;
import com.quind.prueba.tecnica.domain.model.ports.inbound.TaskUseCasePort;
import com.quind.prueba.tecnica.domain.model.ports.outbound.TaskRepositoryPort;
import com.quind.prueba.tecnica.domain.model.utils.ISpecificationTask;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskUpdateDTO;
import com.quind.prueba.tecnica.infrastructure.exception.InvalidParameterException;
import com.quind.prueba.tecnica.infrastructure.exception.TaskServiceException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TasKUseCase implements TaskUseCasePort {

    private final TaskRepositoryPort taskRepositoryPort;
    private final ISpecificationTask iSpecificationTask;

    public TasKUseCase(TaskRepositoryPort taskRepositoryPort, ISpecificationTask iSpecificationTask) {
        this.taskRepositoryPort = taskRepositoryPort;
        this.iSpecificationTask = iSpecificationTask;
    }


    @Override
    public Task createTask(Task task) {
        if(taskRepositoryPort.taskAlreadyExists(task.getTaskCode(), task.getBeginDate())){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST, "Ya existe una tarea con ese codigo y fecha de inicio");
        }
        task.setAddedDate(LocalDate.now());
        iSpecificationTask.createTaskValidations(task);
        return taskRepositoryPort.save(task);
    }

    @Override
    public Task update(TaskUpdateDTO task, Long id) {
        Task taskToUpdate = taskRepositoryPort.findById(id);
        if(taskToUpdate==null){
            throw new TaskServiceException(HttpStatus.NOT_FOUND,"No existe un task registrado con ese id, id: " + id);
        }
        iSpecificationTask.updateTaskValidations(taskToUpdate,task);
        taskToUpdate.setAssignedPerson(task.getAssignedPerson());
        taskToUpdate.setStatus(task.getStatus());
        taskToUpdate.setEndDate(task.getEndDate());
        taskToUpdate.setComment(task.getComment());
        return taskRepositoryPort.update(taskToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Task taskToDelete = taskRepositoryPort.findById(id);
        if(taskToDelete == null){
            throw new TaskServiceException(HttpStatus.NOT_FOUND,"No existe un task registrado con ese id, id: " + id);
        }
        iSpecificationTask.validateDeleteTask(taskToDelete);
        taskRepositoryPort.delete(id);

    }

    @Override
    public List<Task> findAll() {
        return taskRepositoryPort.findAll();
    }

    @Override
    public List<Task> findAllOrderByTaskCode(String order) {
        if (order.equalsIgnoreCase("asc")){
            return taskRepositoryPort.findOrderByTaskCodeAsc();
        }
        else if (order.equalsIgnoreCase("desc")){
            return taskRepositoryPort.findOrderByTaskCodeDesc();
        }
        else throw new InvalidParameterException("Solo se permite asc para bsuqeda acendente o desc para busqueda decendente",HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<Task> findBySomeTopics(Status status, LocalDate startDate, String assignedPerson, Priority priority) {
        return taskRepositoryPort.findBySomeTopics(status,startDate,assignedPerson,priority);
    }

}
