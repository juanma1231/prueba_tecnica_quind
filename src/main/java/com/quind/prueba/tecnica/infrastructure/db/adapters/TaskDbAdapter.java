package com.quind.prueba.tecnica.infrastructure.db.adapters;

import com.quind.prueba.tecnica.domain.model.models.Task;
import com.quind.prueba.tecnica.domain.model.ports.outbound.TaskRepositoryPort;
import com.quind.prueba.tecnica.infrastructure.db.entities.task.TaskEntity;
import com.quind.prueba.tecnica.infrastructure.db.mappers.ITaskEntityMapper;
import com.quind.prueba.tecnica.infrastructure.db.repository.TaskRepository;
import com.quind.prueba.tecnica.infrastructure.exception.TaskServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@Transactional
public class TaskDbAdapter implements TaskRepositoryPort {
    private final ITaskEntityMapper iTaskEntityMapper;
    private final TaskRepository taskRepository;

    public TaskDbAdapter(ITaskEntityMapper iTaskEntityMapper, TaskRepository taskRepository) {
        this.iTaskEntityMapper = iTaskEntityMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
        return iTaskEntityMapper.toTask(taskRepository.save(iTaskEntityMapper.toTaskEntity(task)));
    }

    @Override
    public Task update(Task task) {
        return iTaskEntityMapper.toTask(taskRepository.save(iTaskEntityMapper.toTaskEntity(task)));
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public boolean taskAlreadyExists(Long id, LocalDate date) {
        return false;
    }

    @Override
    public Task findById(Long id) {
        Optional<TaskEntity> tasKById = taskRepository.findById(id);
        if(tasKById.isEmpty()){
            throw new TaskServiceException(HttpStatus.NOT_FOUND,"No existe un task registrado con ese id, id: " + id);
        }
        else{
            return iTaskEntityMapper.toTask(tasKById.get());
        }
    }
}
