package com.quind.prueba.tecnica.infrastructure.api.controller;

import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import com.quind.prueba.tecnica.infrastructure.api.controller.response.ResponseController;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskDTO;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskUpdateDTO;
import com.quind.prueba.tecnica.infrastructure.api.handlers.ITaskHandler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    private final ITaskHandler iTaskHandler;


    public TaskController(ITaskHandler iTaskHandler) {
        this.iTaskHandler = iTaskHandler;
    }

    @PostMapping()
    public ResponseEntity<ResponseController> createNewTask(@RequestBody TaskDTO taskRequest){
        TaskDTO taskCreated = iTaskHandler.save(taskRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseController("Tarea creada exitosamente con código: " + taskCreated.getTaskCode(), HttpStatus.OK.value()));
        }

    @PutMapping("/{taskCode}")
    public ResponseEntity<ResponseController> edit(@RequestBody TaskUpdateDTO taskRequest,
                                                   @PathVariable Long taskCode){
        TaskDTO taskEdited = iTaskHandler.update(taskRequest,taskCode);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseController("Edición exitosa para tarea con código: " + taskEdited.getTaskCode(), HttpStatus.OK.value()));
    }
    @GetMapping("/all")
    public ResponseEntity<ResponseController> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseController("Consulta exitosaa",HttpStatus.OK.value(),iTaskHandler.finAll()));
    }
    @GetMapping("/taskcode")
    public ResponseEntity<ResponseController> getAllOrderByTaskCode(@RequestParam(required = false, defaultValue = "asc") String order){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseController("Consulta exitosa", HttpStatus.OK.value()));
    }

    @GetMapping("/complex")
    public ResponseEntity<ResponseController> getTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) String assignedPerson,
            @RequestParam(required = false) Priority priority) {
        List<TaskDTO> tasks = iTaskHandler.findBySomeTopics(status,startDate,assignedPerson,priority);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseController("Consulta exitosa", HttpStatus.OK.value(),tasks));
    }



}


