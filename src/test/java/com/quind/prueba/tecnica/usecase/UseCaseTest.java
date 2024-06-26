package com.quind.prueba.tecnica.usecase;

import com.quind.prueba.tecnica.domain.model.enums.Priority;
import com.quind.prueba.tecnica.domain.model.enums.Status;
import com.quind.prueba.tecnica.domain.model.models.Task;
import com.quind.prueba.tecnica.domain.model.ports.outbound.TaskRepositoryPort;
import com.quind.prueba.tecnica.domain.model.utils.ISpecificationTask;
import com.quind.prueba.tecnica.domain.model.utils.implementation.SpecificationImplementation;
import com.quind.prueba.tecnica.domain.usecase.TasKUseCase;
import com.quind.prueba.tecnica.infrastructure.api.dtos.TaskUpdateDTO;
import com.quind.prueba.tecnica.infrastructure.exception.InvalidParameterException;
import com.quind.prueba.tecnica.infrastructure.exception.TaskServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
@SpringBootTest
public class UseCaseTest {
    @Test
    public void testCreateTaskValidInput() {

        TaskRepositoryPort taskRepositoryPort = mock(TaskRepositoryPort.class);
        ISpecificationTask iSpecificationTask = mock(ISpecificationTask.class);
        TasKUseCase taskUseCase = new TasKUseCase(taskRepositoryPort, iSpecificationTask);
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.EN_PROCESO, LocalDate.now(), LocalDate.now(), "Comment");

        when(taskRepositoryPort.taskAlreadyExists(anyLong(), any(LocalDate.class))).thenReturn(false);
        when(taskRepositoryPort.save(any(Task.class))).thenReturn(task);
        Task result = taskUseCase.createTask(task);


        verify(taskRepositoryPort, times(1)).taskAlreadyExists(anyLong(), any(LocalDate.class));
        verify(iSpecificationTask, times(1)).createTaskValidations(any(Task.class));
        verify(taskRepositoryPort, times(1)).save(any(Task.class));
        assertEquals(task, result);
    }

    @Test
    public void testCreateTaskExistingCodeAndBeginDateThrowsException() {

        TaskRepositoryPort taskRepositoryPort = mock(TaskRepositoryPort.class);
        ISpecificationTask iSpecificationTask = mock(ISpecificationTask.class);
        TasKUseCase taskUseCase = new TasKUseCase(taskRepositoryPort, iSpecificationTask);
        Task task = new Task(1L, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.EN_PROCESO, LocalDate.now(), LocalDate.now(), "Comment");

        when(taskRepositoryPort.taskAlreadyExists(anyLong(), any(LocalDate.class))).thenReturn(true);

        assertThrows(TaskServiceException.class, () -> taskUseCase.createTask(task));
    }

    @Test
    public void testUpdateValidInputUpdatesTaskAndReturnsIt() {

        TaskRepositoryPort taskRepositoryPort = mock(TaskRepositoryPort.class);
        ISpecificationTask iSpecificationTask = mock(ISpecificationTask.class);
        TasKUseCase taskUseCase = new TasKUseCase(taskRepositoryPort, iSpecificationTask);
        TaskUpdateDTO taskUpdateDTO = new TaskUpdateDTO(Status.EN_PROCESO, LocalDate.now(), "John Doe", "Comment");
        Long id = 1L;
        Task taskToUpdate = new Task(id, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.EN_PROCESO, LocalDate.now(), LocalDate.now(), "Comment");
        Task modifiedTask = new Task(id, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.EN_PROCESO, LocalDate.now(), LocalDate.now(), "New Comment");
        when(taskRepositoryPort.findById(id)).thenReturn(taskToUpdate);
        when(iSpecificationTask.updateTask(taskToUpdate, taskUpdateDTO)).thenReturn(modifiedTask);
        when(taskRepositoryPort.update(modifiedTask)).thenReturn(modifiedTask);
        Task result = taskUseCase.update(taskUpdateDTO, id);


        verify(taskRepositoryPort, times(1)).findById(id);
        verify(iSpecificationTask, times(1)).updateTaskValidations(taskToUpdate, taskUpdateDTO);
        verify(taskRepositoryPort, times(1)).update(modifiedTask); // Verifica que el objeto modificado es pasado a update
        assertEquals(modifiedTask, result);
    }


    @Test
    public void testUpdateInvalidInputThrowsTaskServiceException() {

        TaskRepositoryPort taskRepositoryPort = mock(TaskRepositoryPort.class);
        ISpecificationTask iSpecificationTask = mock(ISpecificationTask.class);
        TasKUseCase taskUseCase = new TasKUseCase(taskRepositoryPort, iSpecificationTask);
        TaskUpdateDTO taskUpdateDTO = new TaskUpdateDTO(Status.EN_PROCESO, LocalDate.now(), "John Doe", "Comment");
        Long taskId = 1L;


        when(taskRepositoryPort.findById(taskId)).thenReturn(null);


        assertThrows(TaskServiceException.class, () -> taskUseCase.update(taskUpdateDTO, taskId));
    }

    @Test
    public void testDeleteByIdValidInputDeletesTask() {

        TaskRepositoryPort taskRepositoryPort = mock(TaskRepositoryPort.class);
        ISpecificationTask iSpecificationTask = mock(ISpecificationTask.class);
        TasKUseCase taskUseCase = new TasKUseCase(taskRepositoryPort, iSpecificationTask);
        Long id = 1L;
        Task taskToDelete = new Task(id, "Task description", "John Doe", LocalDate.now(), Priority.ALTA, Status.EN_PROCESO, LocalDate.now(), LocalDate.now(), "Comment");


        when(taskRepositoryPort.findById(id)).thenReturn(taskToDelete);
        doNothing().when(iSpecificationTask).validateDeleteTask(taskToDelete);
        doNothing().when(taskRepositoryPort).delete(id);
        taskUseCase.deleteById(id);


        verify(taskRepositoryPort, times(1)).findById(id);
        verify(iSpecificationTask, times(1)).validateDeleteTask(taskToDelete);
        verify(taskRepositoryPort, times(1)).delete(id);
    }

    @Test
    public void testDeleteByIdInvalidInputThrowsTaskServiceException() {
        // Arrange
        TaskRepositoryPort taskRepositoryPort = mock(TaskRepositoryPort.class);
        ISpecificationTask iSpecificationTask = mock(ISpecificationTask.class);
        TasKUseCase taskUseCase = new TasKUseCase(taskRepositoryPort, iSpecificationTask);
        Long taskId = 1L;


        when(taskRepositoryPort.findById(taskId)).thenReturn(null);


        assertThrows(TaskServiceException.class, () -> taskUseCase.deleteById(taskId));
    }

    @Test
    public void testFindAllReturnsListOfTasks() {
        // Arrange
        TaskRepositoryPort taskRepositoryPort = mock(TaskRepositoryPort.class);
        ISpecificationTask iSpecificationTask = mock(ISpecificationTask.class);
        TasKUseCase taskUseCase = new TasKUseCase(taskRepositoryPort, iSpecificationTask);
        List<Task> tasks = Arrays.asList(
                new Task(1L, "Task 1", "John Doe", LocalDate.now(), Priority.ALTA, Status.EN_PROCESO, LocalDate.now(), LocalDate.now(), "Comment"),
                new Task(2L, "Task 2", "Jane Smith", LocalDate.now(), Priority.BAJA, Status.NUEVA, LocalDate.now(), LocalDate.now(), "Comment")
        );

        when(taskRepositoryPort.findAll()).thenReturn(tasks);
        List<Task> result = taskUseCase.findAll();


        verify(taskRepositoryPort, times(1)).findAll();
        assertEquals(tasks, result);
    }

    @Test
    public void testFindAllOrderByAdditionDateValidOtder() {
        // Arrange
        TaskRepositoryPort taskRepositoryPort = mock(TaskRepositoryPort.class);
        ISpecificationTask iSpecificationTask = mock(ISpecificationTask.class);
        TasKUseCase taskUseCase = new TasKUseCase(taskRepositoryPort, iSpecificationTask);

        List<Task> tasks = Arrays.asList(
                new Task(1L, "Task 1", "John Doe", LocalDate.now(), Priority.ALTA, Status.EN_PROCESO, LocalDate.now(), LocalDate.now(), "Comment"),
                new Task(2L, "Task 2", "Jane Smith", LocalDate.now(), Priority.BAJA, Status.NUEVA, LocalDate.now(), LocalDate.now(), "Comment")
        );
        // Act
        when(taskRepositoryPort.findAllByOrderByAdditionDateAsc()).thenReturn(tasks);
        List<Task> result = taskUseCase.findAllOrderByAdditionDate("asc");


        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindAllOrderByAdditionDateInvalidOtder() {

        TaskRepositoryPort taskRepositoryPort = mock(TaskRepositoryPort.class);
        ISpecificationTask iSpecificationTask = mock(ISpecificationTask.class);
        TasKUseCase taskUseCase = new TasKUseCase(taskRepositoryPort, iSpecificationTask);

        List<Task> tasks = Arrays.asList(
                new Task(1L, "Task 1", "John Doe", LocalDate.now(), Priority.ALTA, Status.EN_PROCESO, LocalDate.now(), LocalDate.now(), "Comment"),
                new Task(2L, "Task 2", "Jane Smith", LocalDate.now(), Priority.BAJA, Status.NUEVA, LocalDate.now(), LocalDate.now(), "Comment")
        );

        assertThrows(InvalidParameterException.class, () -> taskUseCase.findAllOrderByAdditionDate("jo"));
    }

    @Test
    public void testFindBySomeTopics() {

        TaskRepositoryPort taskRepositoryPort = mock(TaskRepositoryPort.class);
        ISpecificationTask iSpecificationTask = mock(ISpecificationTask.class);
        TasKUseCase taskUseCase = new TasKUseCase(taskRepositoryPort, iSpecificationTask);
        Status status = null;
        LocalDate startDate = LocalDate.of(2021, 1, 1);
        String assignedPerson = "John Doe";
        Priority priority = Priority.ALTA;


        List<Task> tasks = taskUseCase.findBySomeTopics(status, startDate, assignedPerson, priority);


        assertNotNull(tasks);
        assertTrue(tasks.isEmpty());
    }




}
