package com.quind.prueba.tecnica.infrastructure.api.mappers;

import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskDTO;
import com.quind.prueba.tecnica.domain.model.models.Task;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskUpdateDTO;

import java.util.List;

public interface ITaskDtoMappers {
    Task toTasK(TaskDTO taskDTO);

    TaskDTO toTaskDto(Task task);

    List<TaskDTO> toTasksDTO(List<Task> tasks);

    List<Task> toTasks(List<TaskDTO> taskDTOS);


}
