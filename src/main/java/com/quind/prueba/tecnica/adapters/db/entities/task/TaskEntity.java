package com.quind.prueba.tecnica.adapters.db.entities.task;

import com.quind.prueba.tecnica.adapters.db.entities.person.PersonEntity;
import com.quind.prueba.tecnica.domain.enums.Status;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "TASK")
public class TaskEntity {
    @Id
    private String taskCode;

    private String description;

    private PersonEntity assignedPerson;
    @Enumerated(EnumType.STRING)
    private Status satus;

    private String additionDate;

    private String beginDate;

    private String endDate;

    private String comment;

    public TaskEntity(String taskCode, String description, PersonEntity assignedPerson, Status satus, String additionDate, String beginDate, String endDate, String comment) {
        this.taskCode = taskCode;
        this.description = description;
        this.assignedPerson = assignedPerson;
        this.satus = satus;
        this.additionDate = additionDate;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.comment = comment;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PersonEntity getAssignedPerson() {
        return assignedPerson;
    }

    public void setAssignedPerson(PersonEntity assignedPerson) {
        this.assignedPerson = assignedPerson;
    }

    public Status getSatus() {
        return satus;
    }

    public void setSatus(Status satus) {
        this.satus = satus;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAdditionDate() {
        return additionDate;
    }

    public void setAdditionDate(String additionDate) {
        this.additionDate = additionDate;
    }
}
