package com.quind.prueba.tecnica.domain.model.utils;

import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import com.quind.prueba.tecnica.domain.model.models.Task;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskUpdateDTO;

import java.time.LocalDate;

public interface ISpecificationTask {

    void createTaskValidations(Task task);
    void validatePriority(Priority priority, LocalDate date,LocalDate endDate);

    void validateEndDate(LocalDate startDate, LocalDate endDate);
    void validateHighPriority(Priority priority, String comment);

    void validateComment(String comment);

    void validateBeginDate(LocalDate beginDate);

    void updateTaskValidations(Task task, TaskUpdateDTO taskUpdateDTO);
    void validateStatus(Status status);
    void validatePriorityAndStatus(Priority priority, Status status);
    void validateDate(LocalDate startDate, LocalDate endDate);
    void validateStatusAndEndDate(LocalDate endDate, Status status);
    void validateassiegnedPerson(Status status, String person);

    void validateDeleteTask(Task task);
    void validateDeleteStatus(Status status);
    void validateLimitTime(LocalDate endDate);
    void valdiatePriorityAndSatusToDelete(Priority priority, Status status);

    Task updateTask(Task task, TaskUpdateDTO taskUpdateDTO);
    
}
