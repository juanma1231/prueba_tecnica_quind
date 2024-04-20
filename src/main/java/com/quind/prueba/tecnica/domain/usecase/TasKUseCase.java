package com.quind.prueba.tecnica.domain.usecase;

import com.quind.prueba.tecnica.domain.model.models.Task;
import com.quind.prueba.tecnica.domain.model.ports.inbound.TaskUseCasePort;
import com.quind.prueba.tecnica.domain.model.ports.outbound.TaskRepositoryPort;
import com.quind.prueba.tecnica.domain.model.utils.ISpecificationTask;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskUpdateDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

        }
        task.setAddedDate(LocalDate.now());
        iSpecificationTask.createTaskValidations(task);
        taskRepositoryPort.save(task);
        return taskRepositoryPort.save(task);
    }

    @Override
    public Task update(TaskUpdateDTO task, Long id) {
        Task taskToUpdate = taskRepositoryPort.findById(id);
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
        iSpecificationTask.validateDeleteTask(taskToDelete);
        taskRepositoryPort.delete(id);

    }
}
