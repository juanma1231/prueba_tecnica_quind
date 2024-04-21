package com.quind.prueba.tecnica.infrastructure.api.controller.response;

public class AutResponse {
    private String message;
    private int statusCode;
    private String jwt;

    public AutResponse(String message, int statusCode, String jwt) {
        this.message = message;
        this.statusCode = statusCode;
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
