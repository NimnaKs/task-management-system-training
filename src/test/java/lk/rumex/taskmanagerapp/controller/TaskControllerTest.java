package lk.rumex.taskmanagerapp.controller;

import lk.rumex.taskmanagerapp.Enum.Priority;
import lk.rumex.taskmanagerapp.Enum.Status;
import lk.rumex.taskmanagerapp.dto.TaskCreateDTO;
import lk.rumex.taskmanagerapp.dto.TaskDTO;
import lk.rumex.taskmanagerapp.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @Mock
    private Authentication authentication;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("testUser");
    }

    @Test
    public void createTask_ShouldReturnCreatedTask() {

        TaskCreateDTO taskCreateDTO = new TaskCreateDTO("Task Title", "Task Description", Priority.HIGH, Status.TO_DO, LocalDate.now(), 1L);
        TaskDTO taskDTO = new TaskDTO(1L, "Task Title", "Task Description", Priority.HIGH, Status.TO_DO, LocalDate.now(), 1L);

        when(taskService.createTask(any(TaskCreateDTO.class))).thenReturn(taskDTO);

        ResponseEntity<TaskDTO> response = taskController.createTask(taskCreateDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(taskDTO, response.getBody());
        verify(taskService).createTask(taskCreateDTO);
    }

    @Test
    public void getTaskById_ShouldReturnTaskIfAuthorized() {

        TaskDTO taskDTO = new TaskDTO(1L, "Task Title", "Task Description", Priority.HIGH, Status.TO_DO, LocalDate.now(), 1L);
        when(taskService.getTaskById(anyLong(), eq("testUser"))).thenReturn(taskDTO);

        ResponseEntity<TaskDTO> response = taskController.getTaskById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(taskDTO, response.getBody());
        verify(taskService).getTaskById(1L, "testUser");
    }

    @Test
    public void updateTask_ShouldReturnUpdatedTask() {

        TaskCreateDTO taskCreateDTO = new TaskCreateDTO("Updated Title", "Updated Description", Priority.MEDIUM, Status.IN_PROGRESS, LocalDate.now(), 1L);
        TaskDTO updatedTaskDTO = new TaskDTO(1L, "Updated Title", "Updated Description", Priority.MEDIUM, Status.IN_PROGRESS, LocalDate.now(), 1L);

        when(taskService.updateTask(anyLong(), any(TaskCreateDTO.class), eq("testUser"))).thenReturn(updatedTaskDTO);

        ResponseEntity<TaskDTO> response = taskController.updateTask(1L, taskCreateDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTaskDTO, response.getBody());
        verify(taskService).updateTask(1L, taskCreateDTO, "testUser");
    }

    @Test
    public void deleteTask_ShouldReturnNoContent() {

        doNothing().when(taskService).deleteTask(anyLong(), eq("testUser"));

        ResponseEntity<Void> response = taskController.deleteTask(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(taskService).deleteTask(1L, "testUser");
    }
}
