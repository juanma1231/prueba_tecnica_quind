package com.quind.prueba.tecnica.infrastructure.db.mappers.implementation;

import com.quind.prueba.tecnica.domain.model.models.Task;
import com.quind.prueba.tecnica.infrastructure.db.entities.task.TaskEntity;
import com.quind.prueba.tecnica.infrastructure.db.mappers.ITaskEntityMapper;

public class TaskEntityMapperImpl implements ITaskEntityMapper {
    @Override
    public TaskEntity toTaskEntity(Task task) {
        return new TaskEntity(task.getTaskCode(),task.getDescription(),task.getAssignedPerson(),task.getStatus(),task.getPriority(),task.getAddedDate(),task.getBeginDate(),task.getEndDate(),task.getComment());
    }

    @Override
    public Task toTask(TaskEntity taskEntity) {
        return new Task(taskEntity.getTaskCode(),taskEntity.getDescription(),taskEntity.getAssignedPerson(),taskEntity.getAdditionDate(),taskEntity.getPriority(),taskEntity.getSatus(),taskEntity.getBeginDate(),taskEntity.getEndDate(),taskEntity.getComment());
    }
}
