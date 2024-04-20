package com.quind.prueba.tecnica.domain.model.utils.implementation;

import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import com.quind.prueba.tecnica.domain.model.models.Task;
import com.quind.prueba.tecnica.domain.model.utils.ISpecificationTask;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskUpdateDTO;
import com.quind.prueba.tecnica.infrastructure.exception.TaskServiceException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SpecificationImplementation implements ISpecificationTask {

    private final int MAX_COMMENT_LENGHT = 200;

    private final int MAX_AMOUNT_DAYS = 15;
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
            throw new TaskServiceException(HttpStatus.BAD_REQUEST, "Si la tarea tiene prioridad alta, la fecha fin no debe superar los dos dias");
        }

    }


    @Override
    public void validateEndDate(LocalDate startDate, LocalDate endDate) {
        if (defferencesPerDays(startDate,endDate)>MAX_AMOUNT_DAYS){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"La duracion de la tarea no debe ser mayor a "+MAX_AMOUNT_DAYS+ " dias");
        }

    }

    @Override
    public void validateHighPriority(Priority priority, String comment) {
        if(priority.equals(Priority.ALTA) && (comment.isEmpty() || comment == null)){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST, "Las tareas con una una prioridad alta deben de llevar un comentario");
        }
    }

    @Override
    public void validateComment(String comment) {
        if(comment != null && comment.length()>MAX_COMMENT_LENGHT){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST, "El tamaño maximo de un comentario es de "+ MAX_COMMENT_LENGHT+" caracteres");
        }
    }

    @Override
    public void validateBeginDate(LocalDate beginDate) {
        if(beginDate.isBefore(LocalDate.now())){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"La fecha de inicio no debe ser menor que la fecha actual");
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
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"No se pueden editar tareas con estado Finalizado ");
        }
    }

    @Override
    public void validatePriorityAndStatus(Priority priority, Status status) {
        if(priority.equals(Priority.ALTA) && status.equals(Status.EN_PROCESO)){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"No se pueden editar tareas con prioridad alta y estado en proceso");
        }

    }

    @Override
    public void validateDate(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"La fecha fin no debe ser menor que la fecha de inicio");
        }
    }

    @Override
    public void validateStatusAndEndDate(LocalDate endDate, Status status) {
        if(endDate.isBefore(LocalDate.now()) && status != null && status != Status.CANCELADO){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"Si la fecha fin es menor que la fecha actual, solo se puede cambiar el estado a cancelado");
        }
    }

    @Override
    public void validateassiegnedPerson(Status status, String person) {
        if (status != Status.NUEVA && person != null){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"Solo se puede modificar la persona asignada, siempre y cuando el estado sea Nuevo");
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
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"No se pueden eliminar tareas con estado Finalizado o En proceso");
        }
    }

    @Override
    public void validateLimitTime(LocalDate endDate) {
        if(endDate.isAfter(LocalDate.now())){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"No se pueden eliminar taras que cumplan con el tiempo limite de ejecucion");
        }
    }

    @Override
    public void valdiatePriorityAndSatusToDelete(Priority priority, Status status) {
        if(priority.equals(Priority.ALTA) && !status.equals(Status.NUEVA)){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"Si una tarea tiene una prioridad alta solo puede eliminarse cuando el estado es Nueva");
        }
    }

    public static Long defferencesPerDays(LocalDate firstDate, LocalDate secondDate){
        return ChronoUnit.DAYS.between(firstDate,secondDate);
    }
}
