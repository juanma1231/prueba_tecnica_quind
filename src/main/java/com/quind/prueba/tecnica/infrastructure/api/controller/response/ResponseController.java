package com.quind.prueba.tecnica.infrastructure.api.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskDTO;

import java.util.List;

public class ResponseController {
    private String message;
    private int statusCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TaskDTO> tasks;


    public ResponseController() {
    }

    public ResponseController(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public ResponseController (String message, int statusCode, List<TaskDTO> taskDTOS) {
        this.message = message;
        this.statusCode = statusCode;
        this.tasks = taskDTOS;
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

    public List<TaskDTO> getTaskDTOs() {
        return tasks;
    }

    public void setTaskDTOs(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
