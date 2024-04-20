package com.quind.prueba.tecnica.infrastructure.api.controller.advice;

import com.quind.prueba.tecnica.infrastructure.api.controller.response.ErrorResponse;
import com.quind.prueba.tecnica.infrastructure.exception.TaskServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)
public class ControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException){
        Map<String, String> errors = new HashMap<>();
        errors.put("Error: ", illegalArgumentException.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errors, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(TaskServiceException.class)
    public ResponseEntity<ErrorResponse> handleTaskException(TaskServiceException taskServiceException){

        Map<String, String> errors = new HashMap<>();
        errors.put("Error: ", taskServiceException.getErrorMessage());
        ErrorResponse errorResponse = new ErrorResponse(errors, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidArguments(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        ErrorResponse errorResponse = new ErrorResponse(errors, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
