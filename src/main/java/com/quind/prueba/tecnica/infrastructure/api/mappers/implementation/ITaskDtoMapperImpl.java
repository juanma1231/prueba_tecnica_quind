package com.quind.prueba.tecnica.infrastructure.api.mappers.implementation;

import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskDTO;
import com.quind.prueba.tecnica.infrastructure.api.mappers.ITaskDtoMappers;
import com.quind.prueba.tecnica.domain.model.models.Task;

public class ITaskDtoMapperImpl implements ITaskDtoMappers {
    @Override
    public Task toTasK(TaskDTO taskDTO) {
        return null;
    }

    @Override
    public TaskDTO toTaskDto(Task task) {
        return null;
    }
}
