package com.quind.prueba.tecnica.infrastructure.api.mappers.implementation;

import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskDTO;
import com.quind.prueba.tecnica.infrastructure.api.mappers.ITaskDtoMappers;
import com.quind.prueba.tecnica.domain.model.models.Task;

public class ITaskDtoMapperImpl implements ITaskDtoMappers {
    @Override
    public Task toTasK(TaskDTO taskDTO) {
        return new Task(taskDTO.getTaskCode(),taskDTO.getDescription(),taskDTO.getAssignedPerson(),taskDTO.getPriority(),taskDTO.getStatus(),taskDTO.getBeginDate(),taskDTO.getEndDate(),taskDTO.getComment());
    }

    @Override
    public TaskDTO toTaskDto(Task task) {
        return new TaskDTO(task.getTaskCode(),task.getDescription(),task.getAssignedPerson(),task.getStatus(),task.getBeginDate(),task.getEndDate(),task.getComment(),task.getPriority());
    }
}
