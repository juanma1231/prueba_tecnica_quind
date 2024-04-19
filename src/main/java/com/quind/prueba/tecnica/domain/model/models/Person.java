package com.quind.prueba.tecnica.domain.model.models;


public class Person {

    private  int documentNum;

    private String documentType;

    private String name;

    private String lastName;

    private String email;

    public Person(int documentNum, String documentType, String name, String lastName, String email) {
        this.documentNum = documentNum;
        this.documentType = documentType;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public int getDocumentNum() {
        return documentNum;
    }

    public void setDocumentNum(int documentNum) {
        this.documentNum = documentNum;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
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
