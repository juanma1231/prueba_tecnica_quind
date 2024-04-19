package com.quind.prueba.tecnica.domain.model.models;

import com.quind.prueba.tecnica.domain.model.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task {
    private UUID taskCode;

    private  String description;

    private Person assignedPerson;

    private Status status;

    private  LocalDateTime beginDate;

    private LocalDateTime endDate;

    private String comment;

    public Task(UUID taskCode, String description, Person assignedPerson, Status status, LocalDateTime beginDate, LocalDateTime endDate, String comment) {
        this.taskCode = taskCode;
        this.description = description;
        this.assignedPerson = assignedPerson;
        this.status = status;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.comment = comment;
    }

    public UUID getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(UUID taskCode) {
        this.taskCode = taskCode;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getAssignedPerson() {
        return assignedPerson;
    }

    public void setAssignedPerson(Person assignedPerson) {
        this.assignedPerson = assignedPerson;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
