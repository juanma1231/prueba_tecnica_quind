package com.quind.prueba.tecnica.infrastructure.api.mappers;

import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskDTO;
import com.quind.prueba.tecnica.domain.model.models.Task;

public interface ITaskDtoMappers {
    Task toTasK(TaskDTO taskDTO);

    TaskDTO toTaskDto(Task task);
}
