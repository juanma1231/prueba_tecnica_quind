package com.quind.prueba.tecnica.infrastructure.api.handlers;

import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskDTO;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskUpdateDTO;

import java.time.LocalDate;
import java.util.List;

public interface ITaskHandler {
    TaskDTO save(TaskDTO taskDTO);

    TaskDTO update(TaskUpdateDTO taskUpdateDTO, Long id);

    List<TaskDTO> finAll();

    List<TaskDTO> findAllOrderByAdditionDate(String order);

    List<TaskDTO> findBySomeTopics(Status status, LocalDate startDate, String assignedPerson, Priority priority);

}
