package com.quind.prueba.tecnica.infrastructure.api.controller;

import com.quind.prueba.tecnica.infrastructure.api.controller.response.ApiResponse;
import com.quind.prueba.tecnica.infrastructure.exception.InvalidParameterException;
import com.quind.prueba.tecnica.infrastructure.exception.TaskServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(TaskServiceException.class)
    public ResponseEntity<ApiResponse<Void>> handleTaskServiceException(TaskServiceException ex){
        HttpStatus status = ex.getErrorCode() != null ? ex.getErrorCode() : HttpStatus.BAD_REQUEST;
        String message = ex.getErrorMessage() != null ? ex.getErrorMessage() : ex.getMessage();
        return ResponseEntity.status(status)
                .body(ApiResponse.error(message, status.value(), null));
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidParameter(InvalidParameterException ex){
        HttpStatus status = ex.getErrorCode() != null ? ex.getErrorCode() : HttpStatus.BAD_REQUEST;
        String message = ex.getMessage() != null ? ex.getMessage() : "Parámetros inválidos";
        return ResponseEntity.status(status)
                .body(ApiResponse.error(message, status.value(), null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(RestExceptionHandler::formatFieldError)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Errores de validación", HttpStatus.BAD_REQUEST.value(), errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnhandled(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Ha ocurrido un error inesperado", HttpStatus.INTERNAL_SERVER_ERROR.value(), List.of(ex.getMessage())));
    }

    private static String formatFieldError(FieldError error){
        return error.getField() + ": " + error.getDefaultMessage();
    }
}
