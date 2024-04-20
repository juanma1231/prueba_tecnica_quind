package com.quind.prueba.tecnica.domain.model.ports.outbound;

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
}
