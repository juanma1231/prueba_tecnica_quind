package com.quind.prueba.tecnica.domain.model.ports.inbound;

import com.quind.prueba.tecnica.domain.model.models.Task;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskUpdateDTO;

public interface TaskUseCasePort {

    Task createTask(Task task);

    Task update(TaskUpdateDTO task, Long id);

    void deleteById(Long id);
}
