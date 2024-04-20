package com.quind.prueba.tecnica.usecase;

import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import com.quind.prueba.tecnica.domain.model.models.Task;
import com.quind.prueba.tecnica.domain.model.ports.outbound.TaskRepositoryPort;
import com.quind.prueba.tecnica.domain.model.utils.ISpecificationTask;
import com.quind.prueba.tecnica.domain.usecase.TasKUseCase;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskUpdateDTO;
import com.quind.prueba.tecnica.infrastructure.exception.TaskServiceException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UseCaseTest {
    @Test
    public void test_createTask_validInput() {

        // Arrange
        TaskRepositoryPort taskRepositoryPort = mock(TaskRepositoryPort.class);
        ISpecificationTask iSpecificationTask = mock(ISpecificationTask.class);
        TasKUseCase taskUseCase = new TasKUseCase(taskRepositoryPort, iSpecificationTask);
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.EN_PROCESO, LocalDate.now(), LocalDate.now(), "Comment");

        // Act
        when(taskRepositoryPort.taskAlreadyExists(anyLong(), any(LocalDate.class))).thenReturn(false);
        when(taskRepositoryPort.save(any(Task.class))).thenReturn(task);
        Task result = taskUseCase.createTask(task);

        // Assert
        verify(taskRepositoryPort, times(1)).taskAlreadyExists(anyLong(), any(LocalDate.class));
        verify(iSpecificationTask, times(1)).createTaskValidations(any(Task.class));
        verify(taskRepositoryPort, times(1)).save(any(Task.class));
        assertEquals(task, result);
    }

    @Test
    public void test_createTask_existingCodeAndBeginDateThrowsException() {
        // Arrange
        TaskRepositoryPort taskRepositoryPort = mock(TaskRepositoryPort.class);
        ISpecificationTask iSpecificationTask = mock(ISpecificationTask.class);
        TasKUseCase taskUseCase = new TasKUseCase(taskRepositoryPort, iSpecificationTask);
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.EN_PROCESO, LocalDate.now(), LocalDate.now(), "Comment");

        // Act
        when(taskRepositoryPort.taskAlreadyExists(anyLong(), any(LocalDate.class))).thenReturn(true);

        // Assert
        assertThrows(TaskServiceException.class, () -> taskUseCase.createTask(task));
    }

    @Test
    public void test_update_validInput_updatesTaskAndReturnsIt() {
        // Arrange
        TaskRepositoryPort taskRepositoryPort = mock(TaskRepositoryPort.class);
        ISpecificationTask iSpecificationTask = mock(ISpecificationTask.class);
        TasKUseCase taskUseCase = new TasKUseCase(taskRepositoryPort, iSpecificationTask);
        TaskUpdateDTO taskUpdateDTO = new TaskUpdateDTO(Status.EN_PROCESO, LocalDate.now(), "John Doe", "Comment");
        Long id = 1L;
        Task taskToUpdate = new Task(id, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.EN_PROCESO, LocalDate.now(), LocalDate.now(), "Comment");

        // Act
        when(taskRepositoryPort.findById(id)).thenReturn(taskToUpdate);
        when(taskRepositoryPort.update(any(Task.class))).thenReturn(taskToUpdate);
        Task result = taskUseCase.update(taskUpdateDTO, id);

        // Assert
        verify(taskRepositoryPort, times(1)).findById(id);
        verify(iSpecificationTask, times(1)).updateTaskValidations(taskToUpdate, taskUpdateDTO);
        verify(taskRepositoryPort, times(1)).update(any(Task.class));
        assertEquals(taskToUpdate, result);
    }

    @Test
    public void test_update_invalidInput_throwsTaskServiceException() {
        // Arrange
        TaskRepositoryPort taskRepositoryPort = mock(TaskRepositoryPort.class);
        ISpecificationTask iSpecificationTask = mock(ISpecificationTask.class);
        TasKUseCase taskUseCase = new TasKUseCase(taskRepositoryPort, iSpecificationTask);
        TaskUpdateDTO taskUpdateDTO = new TaskUpdateDTO(Status.EN_PROCESO, LocalDate.now(), "John Doe", "Comment");
        Long taskId = 1L;

        // Act
        when(taskRepositoryPort.findById(taskId)).thenReturn(null);

        // Assert
        assertThrows(TaskServiceException.class, () -> taskUseCase.update(taskUpdateDTO, taskId));
    }

    @Test
    public void test_deleteById_validInput_deletesTask() {
        // Arrange
        TaskRepositoryPort taskRepositoryPort = mock(TaskRepositoryPort.class);
        ISpecificationTask iSpecificationTask = mock(ISpecificationTask.class);
        TasKUseCase taskUseCase = new TasKUseCase(taskRepositoryPort, iSpecificationTask);
        Long id = 1L;
        Task taskToDelete = new Task(id, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.EN_PROCESO, LocalDate.now(), LocalDate.now(), "Comment");

        // Act
        when(taskRepositoryPort.findById(id)).thenReturn(taskToDelete);
        doNothing().when(iSpecificationTask).validateDeleteTask(taskToDelete);
        doNothing().when(taskRepositoryPort).delete(id);
        taskUseCase.deleteById(id);

        // Assert
        verify(taskRepositoryPort, times(1)).findById(id);
        verify(iSpecificationTask, times(1)).validateDeleteTask(taskToDelete);
        verify(taskRepositoryPort, times(1)).delete(id);
    }

    @Test
    public void test_deleteById_invalidInput_throwsTaskServiceException() {
        // Arrange
        TaskRepositoryPort taskRepositoryPort = mock(TaskRepositoryPort.class);
        ISpecificationTask iSpecificationTask = mock(ISpecificationTask.class);
        TasKUseCase taskUseCase = new TasKUseCase(taskRepositoryPort, iSpecificationTask);
        Long taskId = 1L;

        // Act
        when(taskRepositoryPort.findById(taskId)).thenReturn(null);

        // Assert
        assertThrows(TaskServiceException.class, () -> taskUseCase.deleteById(taskId));
    }

    @Test
    public void test_findAll_returnsListOfTasks() {
        // Arrange
        TaskRepositoryPort taskRepositoryPort = mock(TaskRepositoryPort.class);
        ISpecificationTask iSpecificationTask = mock(ISpecificationTask.class);
        TasKUseCase taskUseCase = new TasKUseCase(taskRepositoryPort, iSpecificationTask);
        List<Task> tasks = Arrays.asList(
                new Task(1L, "Task 1", "John Doe", LocalDate.now(), Priority.ALTA, Status.EN_PROCESO, LocalDate.now(), LocalDate.now(), "Comment"),
                new Task(2L, "Task 2", "Jane Smith", LocalDate.now(), Priority.BAJA, Status.NUEVA, LocalDate.now(), LocalDate.now(), "Comment")
        );

        // Act
        when(taskRepositoryPort.findAll()).thenReturn(tasks);
        List<Task> result = taskUseCase.findAll();

        // Assert
        verify(taskRepositoryPort, times(1)).findAll();
        assertEquals(tasks, result);
    }
}
