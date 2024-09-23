package lk.rumex.taskmanagerapp.controller;

import jakarta.validation.Valid;
import lk.rumex.taskmanagerapp.Enum.Priority;
import lk.rumex.taskmanagerapp.Enum.Status;
import lk.rumex.taskmanagerapp.dto.TaskCreateDTO;
import lk.rumex.taskmanagerapp.dto.TaskDTO;
import lk.rumex.taskmanagerapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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

}
