package com.quind.prueba.tecnica.usecase;

import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import com.quind.prueba.tecnica.domain.model.models.Task;
import com.quind.prueba.tecnica.domain.model.utils.implementation.SpecificationImplementation;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskUpdateDTO;
import com.quind.prueba.tecnica.infrastructure.exception.TaskServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecificationImplementationTest {

    @Test
    public void test_priority_alta_more_than_two_days() {
        SpecificationImplementation specification = new SpecificationImplementation();
        LocalDate endDate = LocalDate.now().plusDays(3);
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.NUEVA, LocalDate.now(), endDate, "Task comment");

        try {
            specification.createTaskValidations(task);
            fail("Validation should fail");
        } catch (TaskServiceException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getErrorCode());
            assertEquals("Si la tarea tiene prioridad alta, la fecha fin no debe superar los dos dias", e.getErrorMessage());
        }
    }

    @Test
    public void test_priority_alta_within_two_days() {
        SpecificationImplementation specification = new SpecificationImplementation();
        LocalDate endDate = LocalDate.now().plusDays(2);
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.NUEVA, LocalDate.now(), endDate, "Task comment");

        try {
            specification.createTaskValidations(task);
        } catch (TaskServiceException e) {
            fail("Validation should pass");
        }
    }

    @Test
    public void test_priority_baja_more_than_two_days() {
        SpecificationImplementation specification = new SpecificationImplementation();
        LocalDate endDate = LocalDate.now().plusDays(3);
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now(), Priority.BAJA, Status.NUEVA, LocalDate.now(), endDate, "Task comment");

        try {
            specification.createTaskValidations(task);
        } catch (TaskServiceException e) {
            fail("Validation should pass");
        }
    }

    @Test
    public void test_priority_alta_with_comment() {
        SpecificationImplementation specification = new SpecificationImplementation();
        LocalDate endDate = LocalDate.now().plusDays(2);
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.NUEVA, LocalDate.now(), endDate, "Task comment");

        try {
            specification.createTaskValidations(task);
        } catch (TaskServiceException e) {
            fail("Validation should pass");
        }
    }

    @Test
    public void test_priority_baja_no_comment() {
        SpecificationImplementation specification = new SpecificationImplementation();
        LocalDate endDate = LocalDate.now().plusDays(2);
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now(), Priority.BAJA, Status.NUEVA, LocalDate.now(), endDate, "");

        try {
            specification.createTaskValidations(task);
        } catch (TaskServiceException e) {
            fail("Validation should pass");
        }
    }

    @Test
    public void test_comment_length_validation() {
        SpecificationImplementation specification = new SpecificationImplementation();
        String comment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod, nunc id aliquam lacinia, nisl nunc tincidunt nunc, nec tincidunt nunc nisl nec nisi. Nulla facilisi. Sed id semper mauris. Nulla facilisi. Sed id semper mauris. Nulla facilisi. Sed id semper mauris. Nulla facilisi. Sed id semper mauris. Nulla facilisi. Sed id semper mauris. Nulla facilisi. Sed id semper mauris. Nulla facilisi. Sed id semper mauris. Nulla facilisi. Sed id semper mauris. Nulla facilisi. Sed id semper mauris. Nulla facilisi. Sed id semper mauris. Nulla facilisi. Sed id semper mauris. Nulla facilisi. Sed id semper mauris. Nulla facilisi. Sed id semper mauris. Nulla facilisi. Sed id semper mauris. Nulla facilisi. Sed id semper mauris.";
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.NUEVA, LocalDate.now(), LocalDate.now().plusDays(5), comment);

        try {
            specification.createTaskValidations(task);
        } catch (TaskServiceException e) {
            fail("Validation should pass");
        }
    }

    @Test
    public void test_begin_date_validation() {
        SpecificationImplementation specification = new SpecificationImplementation();
        LocalDate beginDate = LocalDate.now().plusDays(2);
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.NUEVA, beginDate, LocalDate.now().plusDays(5), "Task comment");

        try {
            specification.createTaskValidations(task);
        } catch (TaskServiceException e) {
            fail("Validation should pass");
        }
    }

    @Test
    public void test_finalizado_status_update_validation() {
        SpecificationImplementation specification = new SpecificationImplementation();
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.FINALIZADO, LocalDate.now(), LocalDate.now().plusDays(5), "Task comment");
        TaskUpdateDTO taskUpdateDTO = new TaskUpdateDTO(Status.ACTIVA, LocalDate.now().plusDays(10), "Jane Smith", "Updated task comment");

        try {
            specification.updateTaskValidations(task, taskUpdateDTO);
            fail("Validation should fail");
        } catch (TaskServiceException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getErrorCode());
            assertEquals("No se pueden editar tareas con estado Finalizado", e.getErrorMessage());
        }
    }
}
