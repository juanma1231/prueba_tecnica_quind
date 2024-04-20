package com.quind.prueba.tecnica.infrastructure.api.dtos;

import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import com.quind.prueba.tecnica.domain.model.models.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class TaskDTO {

    private Long taskCode;

    private  String description;

    private String assignedPerson;

    private Status status;

    private LocalDate beginDate;

    private LocalDate endDate;

    private String comment;

   private Priority priority;

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
}
