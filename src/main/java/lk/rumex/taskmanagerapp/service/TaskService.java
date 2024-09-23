package lk.rumex.taskmanagerapp.service;

import lk.rumex.taskmanagerapp.dto.TaskCreateDTO;
import lk.rumex.taskmanagerapp.dto.TaskDTO;

public interface TaskService {
    TaskDTO createTask(TaskCreateDTO taskCreateDTO);

    TaskDTO assignTask(Long taskId, Long userId);
}
