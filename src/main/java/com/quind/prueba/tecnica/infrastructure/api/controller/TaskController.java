package com.quind.prueba.tecnica.infrastructure.api.controller;

import com.quind.prueba.tecnica.infrastructure.api.controller.response.ResponseController;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskDTO;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskUpdateDTO;
import com.quind.prueba.tecnica.infrastructure.api.handlers.ITaskHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<TaskDTO> taskDTOS = iTaskHandler.finAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseController("Consulta exitosaa",HttpStatus.OK.value(),taskDTOS));
    }
}


