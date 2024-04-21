package com.quind.prueba.tecnica.infrastructure.db.entities.task;

import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "task")
public class TaskEntity {
    @Column(name = "task_code")
    @Id
    private Long taskCode;

    @Column(name = "description")
    private String description;
    @Column(name = "assigned_person")
    private String assignedPerson;
    @Column(name = "satus")
    @Enumerated(EnumType.STRING)
    private Status satus;
    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Column(name = "addition_date")
    private LocalDate additionDate;
    @Column(name = "begin_date")
    private LocalDate beginDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "comment")
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

    public TaskEntity() {
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

    @Override
    public String toString() {
        return "TaskEntity{" +
                "taskCode=" + taskCode +
                ", description='" + description + '\'' +
                ", assignedPerson='" + assignedPerson + '\'' +
                ", satus=" + satus +
                ", priority=" + priority +
                ", additionDate=" + additionDate +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", comment='" + comment + '\'' +
                '}';
    }
}
