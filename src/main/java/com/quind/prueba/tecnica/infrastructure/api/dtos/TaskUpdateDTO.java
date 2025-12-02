package com.quind.prueba.tecnica.infrastructure.api.dtos;

import com.quind.prueba.tecnica.domain.model.enums.Status;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TaskUpdateDTO {
    private Status status;
    @FutureOrPresent(message = "La fecha fin no puede ser anterior a hoy")
    private LocalDate endDate;
    @Size(max = 100, message = "El nombre de la persona asignada no debe superar 100 caracteres")
    private String assignedPerson;
    @Size(max = 200, message = "El comentario no debe superar 200 caracteres")
    private String comment;

    public TaskUpdateDTO() {
    }

    public TaskUpdateDTO(Status status, LocalDate endDate, String assignedPerson, String comment) {
        this.status = status;
        this.endDate = endDate;
        this.assignedPerson = assignedPerson;
        this.comment = comment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getAssignedPerson() {
        return assignedPerson;
    }

    public void setAssignedPerson(String assignedPerson) {
        this.assignedPerson = assignedPerson;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
