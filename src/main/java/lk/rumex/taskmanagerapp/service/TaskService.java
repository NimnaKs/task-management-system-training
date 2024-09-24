package lk.rumex.taskmanagerapp.service;

import lk.rumex.taskmanagerapp.Enum.Priority;
import lk.rumex.taskmanagerapp.Enum.Status;
import lk.rumex.taskmanagerapp.dto.TaskCreateDTO;
import lk.rumex.taskmanagerapp.dto.TaskDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface TaskService {
    TaskDTO createTask(TaskCreateDTO taskCreateDTO);

    Page<TaskDTO> getFilteredTasks(
            int page,
            int size,
            String sortBy,
            String sortDir,
            Status status,
            Priority priority,
            LocalDate dueDate,
            String username
    );

    TaskDTO getTaskById(
            Long id,
            String username
    );

    TaskDTO updateTask(
            Long id,
            TaskCreateDTO taskCreateDTO,
            String username
    );

    void deleteTask(
            Long id,
            String username
    );
}

