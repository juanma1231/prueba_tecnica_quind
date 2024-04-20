package com.quind.prueba.tecnica.domain.model.utils.implementation;

import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import com.quind.prueba.tecnica.domain.model.models.Task;
import com.quind.prueba.tecnica.domain.model.utils.ISpecificationTask;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskUpdateDTO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SpecificationImplementation implements ISpecificationTask {
    @Override
    public void createTaskValidations(Task task) {
        validatePriority(task.getPriority(), task.getEndDate());
        validateEndDate(task.getBeginDate(), task.getEndDate());
        validateHighPriority(task.getPriority(), task.getComment());
        validateComment(task.getComment());
        validateBeginDate(task.getBeginDate());
    }

    @Override
    public void validatePriority(Priority priority, LocalDate date) {
        if(priority.equals(Priority.ALTA) && defferencesPerDays(LocalDate.now(),date)>2){

        }

    }


    @Override
    public void validateEndDate(LocalDate startDate, LocalDate endDate) {
        if (defferencesPerDays(startDate,endDate)>15){

        }

    }

    @Override
    public void validateHighPriority(Priority priority, String comment) {
        if(priority.equals(Priority.ALTA) && (comment.isEmpty() || comment == null)){

        }
    }

    @Override
    public void validateComment(String comment) {
        if(comment != null && comment.length()>200){

        }
    }

    @Override
    public void validateBeginDate(LocalDate beginDate) {
        if(defferencesPerDays(LocalDate.now(), beginDate)<0){

        }

    }

    @Override
    public void updateTaskValidations(Task task, TaskUpdateDTO taskUpdateDTO) {
        validateStatus(task.getStatus());
        validatePriorityAndStatus(task.getPriority(),task.getStatus());
        validateDate(task.getBeginDate(),taskUpdateDTO.getEndDate());
        validateStatusAndEndDate(task.getEndDate() ,taskUpdateDTO.getStatus());
        validateassiegnedPerson(task.getStatus(), taskUpdateDTO.getAssignedPerson());
    }

    @Override
    public void validateStatus(Status status) {
        if(status.equals(Status.FINALIZADO)){

        }
    }

    @Override
    public void validatePriorityAndStatus(Priority priority, Status status) {
        if(priority.equals(Priority.ALTA) && status.equals(Status.EN_PROCESO)){

        }

    }

    @Override
    public void validateDate(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)){

        }
    }

    @Override
    public void validateStatusAndEndDate(LocalDate endDate, Status status) {
        if(endDate.isBefore(LocalDate.now()) && status != null && status != Status.CANCELADO){

        }
    }

    @Override
    public void validateassiegnedPerson(Status status, String person) {
        if (status != Status.NUEVA && person != null){

        }
    }

    @Override
    public void validateDeleteTask(Task task) {
        validateDeleteStatus(task.getStatus());
        validateLimitTime(task.getEndDate());
        valdiatePriorityAndSatusToDelete(task.getPriority(), task.getStatus());
    }

    @Override
    public void validateDeleteStatus(Status status) {
        if(status.equals(Status.FINALIZADO) || status.equals(Status.EN_PROCESO)){

        }
    }

    @Override
    public void validateLimitTime(LocalDate endDate) {
        if(endDate.isAfter(LocalDate.now())){

        }
    }

    @Override
    public void valdiatePriorityAndSatusToDelete(Priority priority, Status status) {
        if(priority.equals(Priority.ALTA) && !status.equals(Status.NUEVA)){

        }
    }

    public static Long defferencesPerDays(LocalDate firstDate, LocalDate secondDate){
        return ChronoUnit.DAYS.between(firstDate,secondDate);
    }
}
