package com.quind.prueba.tecnica.domain.model.ports.inbound;

import com.quind.prueba.tecnica.domain.model.commands.TaskUpdateCommand;
import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import com.quind.prueba.tecnica.domain.model.models.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskUseCasePort {

    Task createTask(Task task);

    Task update(TaskUpdateCommand task, Long id);

    void deleteById(Long id);

    List<Task> findAll();

    List<Task> findAllOrderByAdditionDate(String order);

    List<Task> findBySomeTopics(Status status, LocalDate startDate, String assignedPerson, Priority priority);
}
