package com.quind.prueba.tecnica.domain.model.utils.implementation;

import com.quind.prueba.tecnica.domain.model.commands.TaskUpdateCommand;
import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import com.quind.prueba.tecnica.domain.model.models.Task;
import com.quind.prueba.tecnica.domain.model.utils.ISpecificationTask;
import com.quind.prueba.tecnica.infrastructure.exception.TaskServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
@Component
public class SpecificationImplementation implements ISpecificationTask {

    private final int MAX_COMMENT_LENGHT = 200;

    private final int MAX_AMOUNT_DAYS = 15;
    @Override
    public void createTaskValidations(Task task) {
        validatePriority(task.getPriority(), task.getBeginDate(),task.getEndDate());
        validateEndDate(task.getBeginDate(), task.getEndDate());
        validateHighPriority(task.getPriority(), task.getComment());
        validateComment(task.getComment());
        validateBeginDate(task.getBeginDate());
    }

    @Override
    public void validatePriority(Priority priority, LocalDate beginDate, LocalDate endDate) {
        if(priority == null || beginDate == null || endDate == null){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST, "La prioridad y las fechas son obligatorias");
        }
        if(priority.equals(Priority.ALTA) && defferencesPerDays(beginDate,endDate)>2){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST, "Si la tarea tiene prioridad alta, la fecha fin no debe superar los dos dias");
        }

    }


    @Override
    public void validateEndDate(LocalDate startDate, LocalDate endDate) {
        if(startDate == null || endDate == null){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"Las fechas de inicio y fin son requeridas");
        }
        if (defferencesPerDays(startDate,endDate)>MAX_AMOUNT_DAYS){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"La duracion de la tarea no debe ser mayor a "+MAX_AMOUNT_DAYS+ " dias");
        }

    }

    @Override
    public void validateHighPriority(Priority priority, String comment) {
        if(priority == null){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST, "La prioridad es obligatoria");
        }
        if(priority.equals(Priority.ALTA) && (comment == null || comment.isEmpty())){
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
        if(beginDate == null){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"La fecha de inicio es requerida");
        }
        if(beginDate.isBefore(LocalDate.now())){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"La fecha de inicio no debe ser menor que la fecha actual");
        }

    }

    @Override
    public void updateTaskValidations(Task task, TaskUpdateCommand taskUpdateCommand) {
        validateStatus(task.getStatus());
        validatePriorityAndStatus(task.getPriority(),task.getStatus());
        validateDate(task.getBeginDate(),taskUpdateCommand.getEndDate());
        validateStatusAndEndDate(task.getEndDate() ,taskUpdateCommand.getStatus());
        validateassiegnedPerson(task.getStatus(), taskUpdateCommand.getAssignedPerson());
    }

    @Override
    public void validateStatus(Status status) {
        if(status != null && status.equals(Status.FINALIZADO)){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"No se pueden editar tareas con estado Finalizado ");
        }
    }

    @Override
    public void validatePriorityAndStatus(Priority priority, Status status) {
        if(priority != null && status != null && priority.equals(Priority.ALTA) && status.equals(Status.EN_PROCESO)){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"No se pueden editar tareas con prioridad alta y estado en proceso");
        }

    }

    @Override
    public void validateDate(LocalDate startDate, LocalDate endDate) {
        if(endDate == null){
            return;
        }
        if (startDate != null && endDate.isBefore(startDate)){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"La fecha fin no debe ser menor que la fecha de inicio");
        }
    }

    @Override
    public void validateStatusAndEndDate(LocalDate endDate, Status status) {
        if(endDate == null){
            return;
        }
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
        if(endDate.isBefore(LocalDate.now())){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"No se pueden eliminar taras que cumplan con el tiempo limite de ejecucion");
        }
    }

    @Override
    public void valdiatePriorityAndSatusToDelete(Priority priority, Status status) {
        if(priority.equals(Priority.ALTA) && !status.equals(Status.NUEVA)){
            throw new TaskServiceException(HttpStatus.BAD_REQUEST,"Si una tarea tiene una prioridad alta solo puede eliminarse cuando el estado es Nueva");
        }
    }

    @Override
    public Task updateTask(Task task, TaskUpdateCommand taskUpdateCommand) {
        return task.applyUpdate(taskUpdateCommand);
    }

    public static Long defferencesPerDays(LocalDate firstDate, LocalDate secondDate){
        return ChronoUnit.DAYS.between(firstDate,secondDate);
    }
}
