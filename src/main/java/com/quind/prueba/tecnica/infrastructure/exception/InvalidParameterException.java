package com.quind.prueba.tecnica.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class InvalidParameterException extends RuntimeException{
    private HttpStatus errorCode;
    private String errorMessage;

    public InvalidParameterException(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public InvalidParameterException(String message, HttpStatus errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
