package com.quind.prueba.tecnica.infrastructure.api.controller.advice;

import com.quind.prueba.tecnica.infrastructure.api.controller.response.ErrorResponse;
import com.quind.prueba.tecnica.infrastructure.exception.InvalidParameterException;
import com.quind.prueba.tecnica.infrastructure.exception.TaskServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)
public class ControllerAdvice {
    private static final Logger log = LogManager.getLogger(ControllerAdvice.class);
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException){
        log.error(illegalArgumentException.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("Error: ", illegalArgumentException.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errors, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(TaskServiceException.class)
    public ResponseEntity<ErrorResponse> handleTaskException(TaskServiceException taskServiceException){
        log.error(taskServiceException.getErrorMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("Error: ", taskServiceException.getErrorMessage());
        ErrorResponse errorResponse = new ErrorResponse(errors, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundExeption(UsernameNotFoundException usernameNotFoundException){
        log.error(usernameNotFoundException.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("Error: ", usernameNotFoundException.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errors, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ErrorResponse> handleTaskException(InvalidParameterException invalidParameterException){
        log.error(invalidParameterException.getErrorMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("Error: ", invalidParameterException.getErrorMessage());
        ErrorResponse errorResponse = new ErrorResponse(errors, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


}
