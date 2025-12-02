package com.quind.prueba.tecnica.domain.model.commands;

import com.quind.prueba.tecnica.domain.model.enums.Status;

import java.time.LocalDate;

public class TaskUpdateCommand {
    private final Status status;
    private final LocalDate endDate;
    private final String assignedPerson;
    private final String comment;

    public TaskUpdateCommand(Status status, LocalDate endDate, String assignedPerson, String comment) {
        this.status = status;
        this.endDate = endDate;
        this.assignedPerson = assignedPerson;
        this.comment = comment;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getAssignedPerson() {
        return assignedPerson;
    }

    public String getComment() {
        return comment;
    }
}
