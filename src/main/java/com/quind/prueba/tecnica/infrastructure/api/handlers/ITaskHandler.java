package com.quind.prueba.tecnica.infrastructure.api.handlers;

import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskDTO;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskUpdateDTO;

import java.util.List;

public interface ITaskHandler {
    TaskDTO save(TaskDTO taskDTO);

    TaskDTO update(TaskUpdateDTO taskUpdateDTO, Long id);

    List<TaskDTO> finAll();
}
