package com.quind.prueba.tecnica.infrastructure.db.mappers.implementation;

import com.quind.prueba.tecnica.domain.model.models.Task;
import com.quind.prueba.tecnica.infrastructure.db.entities.task.TaskEntity;
import com.quind.prueba.tecnica.infrastructure.db.mappers.ITaskEntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskEntityMapperImpl implements ITaskEntityMapper {
    @Override
    public TaskEntity toTaskEntity(Task task) {
        return new TaskEntity(task.getTaskCode(),task.getDescription(),task.getAssignedPerson(),task.getStatus(),task.getPriority(),task.getAddedDate(),task.getBeginDate(),task.getEndDate(),task.getComment());
    }

    @Override
    public Task toTask(TaskEntity taskEntity) {
        return new Task(taskEntity.getTaskCode(),taskEntity.getDescription(),taskEntity.getAssignedPerson(),taskEntity.getAdditionDate(),taskEntity.getPriority(),taskEntity.getSatus(),taskEntity.getBeginDate(),taskEntity.getEndDate(),taskEntity.getComment());
    }

    @Override
    public List<Task> toTasks(List<TaskEntity> taskEntities) {
        return taskEntities.stream().map(this::toTask).toList();
    }

    @Override
    public List<TaskEntity> toTaskEntities(List<Task> tasks) {
        return tasks.stream().map(this::toTaskEntity).toList();
    }
}
