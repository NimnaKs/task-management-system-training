package lk.rumex.taskmanagerapp.conversion;

import lk.rumex.taskmanagerapp.dto.TaskCreateDTO;
import lk.rumex.taskmanagerapp.dto.TaskDTO;
import lk.rumex.taskmanagerapp.dto.UserCreateDTO;
import lk.rumex.taskmanagerapp.dto.UserDTO;
import lk.rumex.taskmanagerapp.entity.Task;
import lk.rumex.taskmanagerapp.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ConversionData {
    final private ModelMapper modelMapper;
    public User convertToUserEntity(Optional<UserCreateDTO> userCreateDTO){
        return modelMapper.map(userCreateDTO, User.class);
    }

    public UserDTO convertToUserDTO(Optional<User> user){
        return modelMapper.map(user,UserDTO.class);
    }

    public List<UserDTO> getUserDTOList(List<User> users){
        return modelMapper.map(users,List.class);
    }

    public List<User> getUserEntityList(List<UserCreateDTO> userCreateDTOS){
        return modelMapper.map(userCreateDTOS,List.class);
    }

    public Task convertToTaskEntity(Optional<TaskCreateDTO> taskCreateDTO){
        return modelMapper.map(taskCreateDTO, Task.class);
    }

    public TaskDTO convertToTaskDTO(Optional<Task> task){
        return modelMapper.map(task,TaskDTO.class);
    }

    public List<TaskDTO> getTaskDTOList(List<Task> tasks){
        return modelMapper.map(tasks,List.class);
    }

    public List<Task> getTaskEntityList(List<TaskCreateDTO> taskCreateDTOS){
        return modelMapper.map(taskCreateDTOS,List.class);
    }
}
