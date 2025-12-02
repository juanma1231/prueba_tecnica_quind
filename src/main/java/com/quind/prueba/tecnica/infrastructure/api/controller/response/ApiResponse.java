package com.quind.prueba.tecnica.infrastructure.api.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private final String message;
    private final int statusCode;
    private final T data;
    private final List<String> errors;

    private ApiResponse(String message, int statusCode, T data, List<String> errors) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
        this.errors = errors;
    }

    public static <T> ApiResponse<T> success(String message, int statusCode, T data) {
        return new ApiResponse<>(message, statusCode, data, null);
    }

    public static ApiResponse<Void> success(String message, int statusCode) {
        return new ApiResponse<>(message, statusCode, null, null);
    }

    public static ApiResponse<Void> error(String message, int statusCode, List<String> errors) {
        return new ApiResponse<>(message, statusCode, null, errors);
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public T getData() {
        return data;
    }

    public List<String> getErrors() {
        return errors;
    }
}
