package com.quind.prueba.tecnica.domain.model.models;

import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Task {
    private Long taskCode;

    private  String description;

    private String assignedPerson;

    private LocalDate addedDate;

    private Priority priority;

    private Status status;

    private LocalDate beginDate;

    private LocalDate endDate;

    private String comment;

    public Task(Long taskCode, String description, String assignedPerson, LocalDate addedDate, Priority priority, Status status, LocalDate beginDate, LocalDate endDate, String comment) {
        this.taskCode = taskCode;
        this.description = description;
        this.assignedPerson = assignedPerson;
        this.addedDate = addedDate;
        this.priority = priority;
        this.status = status;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.comment = comment;
    }

    public Task(Long taskCode, String description, String assignedPerson, Priority priority, Status status, LocalDate beginDate, LocalDate endDate, String comment) {
        this.taskCode = taskCode;
        this.description = description;
        this.assignedPerson = assignedPerson;
        this.priority = priority;
        this.status = status;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.comment = comment;
    }

    public Long getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(Long taskCode) {
        this.taskCode = taskCode;
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

    public void setAssignedPerson(String assignedPerson) {
        this.assignedPerson = assignedPerson;
    }

    public LocalDate getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDaate) {
        this.addedDate = addedDaate;
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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
