package com.quind.prueba.tecnica.infrastructure.api.handlers.implementation;

import com.quind.prueba.tecnica.domain.model.ports.inbound.TaskUseCasePort;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskDTO;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskUpdateDTO;
import com.quind.prueba.tecnica.infrastructure.api.handlers.ITaskHandler;
import com.quind.prueba.tecnica.infrastructure.api.mappers.ITaskDtoMappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskHandlerImpl implements ITaskHandler {

    private final TaskUseCasePort taskUseCasePort;

    private final ITaskDtoMappers iTaskDtoMappers;

    public TaskHandlerImpl(TaskUseCasePort taskUseCasePort, ITaskDtoMappers iTaskDtoMappers) {
        this.taskUseCasePort = taskUseCasePort;
        this.iTaskDtoMappers = iTaskDtoMappers;
    }

    @Override
    public TaskDTO save(TaskDTO taskDTO) {
        return iTaskDtoMappers.toTaskDto(taskUseCasePort.createTask(iTaskDtoMappers.toTasK(taskDTO)));
    }

    @Override
    public TaskDTO update(TaskUpdateDTO taskUpdateDTO, Long id) {
        return iTaskDtoMappers.toTaskDto(taskUseCasePort.update(taskUpdateDTO,id));
    }

    @Override
    public List<TaskDTO> finAll() {
        return iTaskDtoMappers.toTasksDTO(taskUseCasePort.findAll());
    }
}

