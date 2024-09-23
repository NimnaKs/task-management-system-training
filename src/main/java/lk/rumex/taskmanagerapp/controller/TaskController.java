package lk.rumex.taskmanagerapp.controller;
import jakarta.validation.Valid;
import lk.rumex.taskmanagerapp.dto.TaskCreateDTO;
import lk.rumex.taskmanagerapp.dto.TaskDTO;
import lk.rumex.taskmanagerapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<TaskDTO> assignTask(@PathVariable Long taskId, @PathVariable Long userId) {
        TaskDTO task = taskService.assignTask(taskId, userId);
        return ResponseEntity.ok(task);
    }




}
