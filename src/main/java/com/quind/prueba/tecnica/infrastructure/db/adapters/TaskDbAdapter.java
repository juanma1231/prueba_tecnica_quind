package com.quind.prueba.tecnica.infrastructure.db.adapters;

import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import com.quind.prueba.tecnica.domain.model.models.Task;
import com.quind.prueba.tecnica.domain.model.ports.outbound.TaskRepositoryPort;
import com.quind.prueba.tecnica.infrastructure.db.entities.task.TaskEntity;
import com.quind.prueba.tecnica.infrastructure.db.mappers.ITaskEntityMapper;
import com.quind.prueba.tecnica.infrastructure.db.repository.TaskRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class TaskDbAdapter implements TaskRepositoryPort {
    private final ITaskEntityMapper iTaskEntityMapper;
    private final TaskRepository taskRepository;
    private static final Logger log = LogManager.getLogger(TaskDbAdapter.class);

    public TaskDbAdapter(ITaskEntityMapper iTaskEntityMapper, TaskRepository taskRepository) {
        this.iTaskEntityMapper = iTaskEntityMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
        log.info("se guarda una tarea");
        return iTaskEntityMapper.toTask(taskRepository.save(iTaskEntityMapper.toTaskEntity(task)));
    }

    @Override
    public Task update(Task task) {
        log.info("se editó una tarea");
        return iTaskEntityMapper.toTask(taskRepository.save(iTaskEntityMapper.toTaskEntity(task)));
    }

    @Override
    public void delete(Long id) {
        log.info("se eliminó una tarea");
        taskRepository.deleteById(id);
    }

    @Override
    public boolean taskAlreadyExists(Long id, LocalDate date) {
        log.info("se consultó la existencia de una tarea en especifico");
        Optional<TaskEntity> taskEntity= taskRepository.findByTaskCodeAndEndDate(id,date);
        if(taskEntity.isEmpty()){
            return false;
        }else return true;

    }

    @Override
    public Task findById(Long id) {
        log.info("se consultó la existencia de una tarea en especifico");
        Optional<TaskEntity> tasKById = taskRepository.findById(id);
        return tasKById.map(iTaskEntityMapper::toTask).orElse(null);
    }

    @Override
    public List<Task> findAll() {
        log.info("se consultaron todas las tareas");
        return iTaskEntityMapper.toTasks(taskRepository.findAll());
    }

    @Override
    public List<Task> findAllByOrderByAdditionDateAsc() {
        log.info("se consultaron todas las tareas");
        return iTaskEntityMapper.toTasks(taskRepository.findAllByOrderByAdditionDateAsc());
    }

    @Override
    public List<Task> findAllByOrderByAdditionDateDesc() {
        log.info("se consultaron todas las tareas");
        return iTaskEntityMapper.toTasks(taskRepository.findAllByOrderByAdditionDateDesc());
    }

    @Override
    public List<Task> findBySomeTopics(Status status, LocalDate startDate, String assignedPerson, Priority priority) {
        log.info("se consultaron algunas tareas");
        return iTaskEntityMapper.toTasks(taskRepository.findByComplexQuery(status,startDate,assignedPerson,priority));
    }
}
