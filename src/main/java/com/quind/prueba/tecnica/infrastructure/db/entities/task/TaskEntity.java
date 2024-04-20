package com.quind.prueba.tecnica.infrastructure.db.entities.task;

import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "TASK")
public class TaskEntity {
    @Id
    private Long taskCode;

    private String description;

    private String assignedPerson;
    @Enumerated(EnumType.STRING)
    private Status satus;
    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDate additionDate;

    private LocalDate beginDate;

    private LocalDate endDate;

    private String comment;

    public TaskEntity(Long taskCode, String description, String assignedPerson, Status satus, Priority priority, LocalDate additionDate, LocalDate beginDate, LocalDate endDate, String comment) {
        this.taskCode = taskCode;
        this.description = description;
        this.assignedPerson = assignedPerson;
        this.satus = satus;
        this.priority = priority;
        this.additionDate = additionDate;
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

    public Status getSatus() {
        return satus;
    }

    public void setSatus(Status satus) {
        this.satus = satus;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getAdditionDate() {
        return additionDate;
    }

    public void setAdditionDate(LocalDate additionDate) {
        this.additionDate = additionDate;
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
