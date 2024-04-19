package com.quind.prueba.tecnica.infrastructure.db.mappers;

import com.quind.prueba.tecnica.infrastructure.db.entities.task.TaskEntity;
import com.quind.prueba.tecnica.domain.model.models.Task;

public interface ITaskEntityMapper {
    TaskEntity toTaskEntity(Task task);

    Task toTask(TaskEntity taskEntity);
}
