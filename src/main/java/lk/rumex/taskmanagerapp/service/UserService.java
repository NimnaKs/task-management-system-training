package lk.rumex.taskmanagerapp.service;

import lk.rumex.taskmanagerapp.dto.UserCreateDTO;
import lk.rumex.taskmanagerapp.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserCreateDTO userCreateDTO);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO updateUser(Long id, UserCreateDTO userCreateDTO);

    void deleteUser(Long id);
}
