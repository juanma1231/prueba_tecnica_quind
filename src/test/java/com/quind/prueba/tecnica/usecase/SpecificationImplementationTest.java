package com.quind.prueba.tecnica.usecase;

import com.quind.prueba.tecnica.domain.model.commands.TaskUpdateCommand;
import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import com.quind.prueba.tecnica.domain.model.models.Task;
import com.quind.prueba.tecnica.domain.model.utils.implementation.SpecificationImplementation;
import com.quind.prueba.tecnica.infrastructure.exception.TaskServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SpecificationImplementationTest {

    @InjectMocks
    private SpecificationImplementation specification;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTaskValidationsValidTaskNoException() {
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.NUEVA, LocalDate.now(), LocalDate.now().plusDays(1), "Valid comment");
        assertDoesNotThrow(() -> specification.createTaskValidations(task));
    }

    @Test
    void createTaskValidationsCommentTooLongThrowsException() {
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.NUEVA, LocalDate.now(), LocalDate.now().plusDays(1), "a".repeat(251));
        assertThrows(TaskServiceException.class, () -> specification.createTaskValidations(task));

    }

    @Test
    void createTaskValidationsBeginDateBeforeCurrentThrowsException() {
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now().minusDays(1), Priority.ALTA, Status.NUEVA, LocalDate.now().minusDays(1), LocalDate.now(), "Valid comment");
        assertThrows(TaskServiceException.class, () -> specification.createTaskValidations(task));

    }

    @Test
    void validateDeleteTaskTaskCanBeDeletedNoException() {
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now().plusDays(1), Priority.BAJA, Status.NUEVA, LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), "Comment");
        assertDoesNotThrow(() -> specification.validateDeleteTask(task));
    }

    @Test
    void validateDeleteTaskTaskCannotBeDeletedDueToStatusThrowsException() {
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.FINALIZADO, LocalDate.now(), LocalDate.now().plusDays(5), "Comment");
        assertThrows(TaskServiceException.class, () -> specification.validateDeleteTask(task));

    }

    @Test
    void updateTaskValidationsTaskFinalizedThrowsException() {
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now(), Priority.MEDIA, Status.FINALIZADO, LocalDate.now(), LocalDate.now().plusDays(1), "Comment");
        TaskUpdateCommand taskUpdateDTO = new TaskUpdateCommand(Status.ACTIVA, LocalDate.now().plusDays(1), "Jane Doe", "Updated comment");

        assertThrows(TaskServiceException.class, () -> specification.updateTaskValidations(task, taskUpdateDTO));

    }


    @Test
    void updateTaskValidationsHighPriorityAndInProgressThrowsException() {
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.EN_PROCESO, LocalDate.now(), LocalDate.now().plusDays(1), "Comment");
        TaskUpdateCommand taskUpdateDTO = new TaskUpdateCommand(Status.EN_PROCESO, LocalDate.now().plusDays(1), "Jane Doe", "Updated comment");

        assertThrows(TaskServiceException.class, () -> specification.updateTaskValidations(task, taskUpdateDTO));

    }


    @Test
    void updateTaskValidationsEndDateBeforeStartDateThrowsException() {
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now().plusDays(1), Priority.MEDIA, Status.NUEVA, LocalDate.now(), LocalDate.now().plusDays(1), "Comment");
        TaskUpdateCommand taskUpdateDTO = new TaskUpdateCommand(Status.NUEVA, LocalDate.now().minusDays(1), "Jane Doe", "Updated comment");

        assertThrows(TaskServiceException.class, () -> specification.validateDate(task.getBeginDate(), taskUpdateDTO.getEndDate()));

    }

    @Test
    void updateTaskValidationsEndDatePassedAndStatusNotCanceledThrowsException() {
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now().minusDays(2), Priority.MEDIA, Status.NUEVA, LocalDate.now().minusDays(5), LocalDate.now().minusDays(2), "Comment");
        TaskUpdateCommand taskUpdateDTO = new TaskUpdateCommand(Status.ACTIVA, LocalDate.now().plusDays(1), "Jane Doe", "Updated comment");

        assertThrows(TaskServiceException.class, () -> specification.updateTaskValidations(task, taskUpdateDTO));
    }
}



