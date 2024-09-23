package lk.rumex.taskmanagerapp.service.IMPL;

import lk.rumex.taskmanagerapp.conversion.ConversionData;
import lk.rumex.taskmanagerapp.dto.TaskCreateDTO;
import lk.rumex.taskmanagerapp.dto.TaskDTO;
import lk.rumex.taskmanagerapp.entity.Task;
import lk.rumex.taskmanagerapp.entity.User;
import lk.rumex.taskmanagerapp.exception.ResourceNotFoundException;
import lk.rumex.taskmanagerapp.repository.TaskRepository;
import lk.rumex.taskmanagerapp.repository.UserRepository;
import lk.rumex.taskmanagerapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceIMPL implements TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final ConversionData conversionData;

    public TaskDTO createTask(TaskCreateDTO taskCreateDTO) {
        Task task = conversionData.convertToTaskEntity(Optional.ofNullable(taskCreateDTO));
        if (taskCreateDTO.getAssignedUserId() != null) {
            User user = userRepository.findById(taskCreateDTO.getAssignedUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + taskCreateDTO.getAssignedUserId()));
            task.setAssignedUser(user);
        }

        Task savedTask = taskRepository.save(task);
        return conversionData.convertToTaskDTO(Optional.of(savedTask));
    }
}
