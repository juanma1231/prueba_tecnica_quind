package com.quind.prueba.tecnica.infrastructure.api.dtos;

import java.util.UUID;

public class PersonDTO {
    private UUID id;

    private String documentType;

    private Integer documentNum;

    private String name;

    private String lastName;

    private String email;

    public PersonDTO(UUID id, String documentType, Integer documentNum, String name, String lastName, String email) {
        this.id = id;
        this.documentType = documentType;
        this.documentNum = documentNum;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Integer getDocumentNum() {
        return documentNum;
    }

    public void setDocumentNum(Integer documentNum) {
        this.documentNum = documentNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
