package com.quind.prueba.tecnica.infrastructure.api.controller;

import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import com.quind.prueba.tecnica.infrastructure.api.controller.response.ApiResponse;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskDTO;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskUpdateDTO;
import com.quind.prueba.tecnica.infrastructure.api.handlers.ITaskHandler;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/task")
public class TaskController {

    private final ITaskHandler iTaskHandler;


    public TaskController(ITaskHandler iTaskHandler) {
        this.iTaskHandler = iTaskHandler;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<Void>> createNewTask(@RequestBody @Valid TaskDTO taskRequest){
        TaskDTO taskCreated = iTaskHandler.save(taskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Tarea creada exitosamente con código: " + taskCreated.getTaskCode(), HttpStatus.CREATED.value()));
    }

    @PutMapping("/{taskCode}")
    public ResponseEntity<ApiResponse<Void>> edit(@RequestBody @Valid TaskUpdateDTO taskRequest,
                                                   @PathVariable Long taskCode){
        TaskDTO taskEdited = iTaskHandler.update(taskRequest,taskCode);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Edición exitosa para tarea con código: " + taskEdited.getTaskCode(), HttpStatus.OK.value()));
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<TaskDTO>>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Consulta exitosa",HttpStatus.OK.value(),iTaskHandler.finAll()));
    }
    @GetMapping("/aditionDate")
    public ResponseEntity<ApiResponse<List<TaskDTO>>> getAllOrderByTaskCode(@RequestParam(required = false, defaultValue = "asc") String order){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Consulta exitosa", HttpStatus.OK.value(),iTaskHandler.findAllOrderByAdditionDate(order)));
    }
    @DeleteMapping("/delete/{taskCode}")
    public ResponseEntity<ApiResponse<Void>> deleteTaskById( @PathVariable Long taskCode){
        iTaskHandler.deleteById(taskCode);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Tarea eliminada con exito",HttpStatus.OK.value()));
    }


    @GetMapping("/complex")
    public ResponseEntity<ApiResponse<List<TaskDTO>>> getTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) String assignedPerson,
            @RequestParam(required = false) Priority priority) {
        List<TaskDTO> tasks = iTaskHandler.findBySomeTopics(status,startDate,assignedPerson,priority);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Consulta exitosa", HttpStatus.OK.value(),tasks));
    }



}


