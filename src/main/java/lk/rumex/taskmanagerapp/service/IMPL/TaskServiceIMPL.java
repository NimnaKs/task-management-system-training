package lk.rumex.taskmanagerapp.service.IMPL;

import lk.rumex.taskmanagerapp.dto.TaskCreateDTO;
import lk.rumex.taskmanagerapp.dto.TaskDTO;
import lk.rumex.taskmanagerapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceIMPL implements TaskService {
    @Override
    public TaskDTO createTask(TaskCreateDTO taskCreateDTO) {
        return null;
    }

    @Override
    public TaskDTO assignTask(Long taskId, Long userId) {
        return null;
    }
}
