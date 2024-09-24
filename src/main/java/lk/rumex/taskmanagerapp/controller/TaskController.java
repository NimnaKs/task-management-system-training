package lk.rumex.taskmanagerapp.controller;

import jakarta.validation.Valid;
import lk.rumex.taskmanagerapp.Enum.Priority;
import lk.rumex.taskmanagerapp.Enum.Status;
import lk.rumex.taskmanagerapp.dto.TaskCreateDTO;
import lk.rumex.taskmanagerapp.dto.TaskDTO;
import lk.rumex.taskmanagerapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskCreateDTO taskCreateDTO) {
        TaskDTO createdTask = taskService.createTask(taskCreateDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<TaskDTO>> getFilteredTasks(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortBy", defaultValue = "dueDate") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
            @RequestParam(value = "status", required = false) Status status,
            @RequestParam(value = "priority", required = false) Priority priority,
            @RequestParam(value = "dueDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate
            ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Page<TaskDTO> taskPage = taskService.getFilteredTasks(
                page,
                size,
                sortBy,
                sortDir,
                status,
                priority,
                dueDate,
                getUsernameFromAuthentication(authentication)
        );
        return ResponseEntity.ok(taskPage);
    }

    private String getUsernameFromAuthentication(Authentication authentication) {
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = getUsernameFromAuthentication(authentication);
        TaskDTO task = taskService.getTaskById(id, username);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @Valid @RequestBody TaskCreateDTO taskCreateDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = getUsernameFromAuthentication(authentication);
        TaskDTO updatedTask = taskService.updateTask(id, taskCreateDTO, username);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = getUsernameFromAuthentication(authentication);
        taskService.deleteTask(id, username);
        return ResponseEntity.noContent().build();
    }


}
