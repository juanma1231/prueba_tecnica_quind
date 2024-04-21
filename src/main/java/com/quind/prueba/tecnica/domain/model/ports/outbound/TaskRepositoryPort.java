package com.quind.prueba.tecnica.domain.model.ports.outbound;

import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import com.quind.prueba.tecnica.domain.model.models.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepositoryPort {
    Task save(Task task);
    Task update(Task task);
    void delete(Long id);
    boolean taskAlreadyExists(Long id, LocalDate date);
    Task findById(Long id);
    List<Task> findAll();
    List<Task> findAllByOrderByAdditionDateAsc();
    List<Task> findAllByOrderByAdditionDateDesc();

    List<Task> findBySomeTopics(Status status, LocalDate startDate, String assignedPerson, Priority priority);
}
