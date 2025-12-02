package com.quind.prueba.tecnica.infrastructure.api.mappers;

import com.quind.prueba.tecnica.domain.model.commands.TaskUpdateCommand;
import com.quind.prueba.tecnica.domain.model.models.Task;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskDTO;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ITaskDtoMappers {
    @Mapping(target = "addedDate", ignore = true)
    Task toTask(TaskDTO taskDTO);

    TaskDTO toTaskDto(Task task);

    List<TaskDTO> toTasksDTO(List<Task> tasks);

    List<Task> toTasks(List<TaskDTO> taskDTOS);

    TaskUpdateCommand toUpdateCommand(TaskUpdateDTO taskUpdateDTO);
}
