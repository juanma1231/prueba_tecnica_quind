package com.quind.prueba.tecnica.infrastructure.api.dtos;

import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TaskDTO {

    @NotNull(message = "El codigo de la tarea es obligatorio")
    @Positive(message = "El codigo de la tarea debe ser mayor a cero")
    private Long taskCode;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 255, message = "La descripción no debe superar los 255 caracteres")
    private  String description;

    @NotBlank(message = "La persona asignada es obligatoria")
    @Size(max = 100, message = "La persona asignada no debe superar 100 caracteres")
    private String assignedPerson;

    @NotNull(message = "El estado es obligatorio")
    private Status status;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @FutureOrPresent(message = "La fecha de inicio no puede ser anterior a hoy")
    private LocalDate beginDate;

    @NotNull(message = "La fecha de fin es obligatoria")
    @FutureOrPresent(message = "La fecha de fin no puede ser anterior a hoy")
    private LocalDate endDate;

    @Size(max = 200, message = "El comentario no debe superar 200 caracteres")
    private String comment;

    @NotNull(message = "La prioridad es obligatoria")
   private Priority priority;

    public TaskDTO() {
    }

    public TaskDTO(Long taskCode, String description, String assignedPerson, Status status, LocalDate beginDate, LocalDate endDate, String comment, Priority priority) {
        this.taskCode = taskCode;
        this.description = description;
        this.assignedPerson = assignedPerson;
        this.status = status;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.comment = comment;
        this.priority = priority;
    }

    public Long getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(Long taskCode) {
        this.taskCode = taskCode;
    }

    public void setAssignedPerson(String assignedPerson) {
        this.assignedPerson = assignedPerson;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignedPerson() {
        return assignedPerson;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "taskCode=" + taskCode +
                ", description='" + description + '\'' +
                ", assignedPerson='" + assignedPerson + '\'' +
                ", status=" + status +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", comment='" + comment + '\'' +
                ", priority=" + priority +
                '}';
    }
}
