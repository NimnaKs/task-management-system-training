package lk.rumex.taskmanagerapp.service.IMPL;

import lk.rumex.taskmanagerapp.Enum.Priority;
import lk.rumex.taskmanagerapp.Enum.Role;
import lk.rumex.taskmanagerapp.Enum.Status;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceIMPL implements TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final ConversionData conversionData;

    public TaskDTO createTask(TaskCreateDTO taskCreateDTO) {
        Task task = new Task();
        task.setTitle(taskCreateDTO.getTitle());
        task.setDescription(taskCreateDTO.getDescription());
        task.setPriority(taskCreateDTO.getPriority());
        task.setStatus(taskCreateDTO.getStatus());
        task.setDueDate(taskCreateDTO.getDueDate());
        if (taskCreateDTO.getAssignedUserId() != null) {
            User user = userRepository.findById(taskCreateDTO.getAssignedUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + taskCreateDTO.getAssignedUserId()));
            task.setAssignedUser(user);
        }

        Task savedTask = taskRepository.save(task);
        return conversionData.convertToTaskDTO(Optional.of(savedTask));
    }

    public Page<TaskDTO> getFilteredTasks(
            int page,
            int size,
            String sortBy,
            String sortDir,
            Status status,
            Priority priority,
            LocalDate dueDate,
            String username) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<Task> taskPage;
        if (username != null && isAdmin(username)) {
            taskPage = taskRepository.findAllWithFilters(status, priority, dueDate, pageable);
        } else {
            User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
            taskPage = taskRepository.findAllByAssignedUserId(
                    user.getId(),
                    status,
                    priority,
                    dueDate,
                    pageable);
        }

        return taskPage.map(conversionData::convertToDTO);
    }

    @Override
    public TaskDTO getTaskById(Long id, String username) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));

        if (!isAdmin(username) && !isAssignedToUser(task, username)) {
            throw new SecurityException("You do not have permission to access this task");
        }

        return conversionData.convertToTaskDTO(Optional.of(task));
    }

    @Override
    public TaskDTO updateTask(Long id, TaskCreateDTO taskCreateDTO, String username) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));

        if (!isAdmin(username) && !isAssignedToUser(task, username)) {
            throw new SecurityException("You do not have permission to update this task");
        }

        task.setTitle(taskCreateDTO.getTitle());
        task.setDescription(taskCreateDTO.getDescription());
        task.setStatus(taskCreateDTO.getStatus());
        task.setPriority(taskCreateDTO.getPriority());
        task.setDueDate(taskCreateDTO.getDueDate());
        if (taskCreateDTO.getAssignedUserId()!= null) {
            User user = userRepository.findById(taskCreateDTO.getAssignedUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + taskCreateDTO.getAssignedUserId()));
            task.setAssignedUser(user);
        }
        Task updatedTask = taskRepository.save(task);
        return conversionData.convertToTaskDTO(Optional.of(updatedTask));
    }

    @Override
    public void deleteTask(Long id, String username) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));

        if (!isAdmin(username) && !isAssignedToUser(task, username)) {
            throw new SecurityException("You do not have permission to delete this task");
        }

        taskRepository.delete(task);
    }

    private boolean isAdmin(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        return user.getRole() == Role.ADMIN;
    }

    private boolean isAssignedToUser(Task task, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username " + username));
        return task.getAssignedUser() != null && task.getAssignedUser().getId().equals(user.getId());
    }

}
