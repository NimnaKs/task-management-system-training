package lk.rumex.taskmanagerapp.controller;

import lk.rumex.taskmanagerapp.Enum.Role;
import lk.rumex.taskmanagerapp.dto.UserCreateDTO;
import lk.rumex.taskmanagerapp.dto.UserDTO;
import lk.rumex.taskmanagerapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUser_ShouldReturnCreatedUser() {

        UserCreateDTO userCreateDTO = new UserCreateDTO("john_doe", "john@example.com", Role.ADMIN, "password123");
        UserDTO userDTO = new UserDTO(1L, "john_doe", "john@example.com", Role.ADMIN, "encodedPassword");

        when(userService.createUser(any(UserCreateDTO.class))).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.createUser(userCreateDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
        verify(userService).createUser(userCreateDTO);
    }

    @Test
    public void getAllUsers_ShouldReturnListOfUsers() {

        UserDTO user1 = new UserDTO(1L, "john_doe", "john@example.com", Role.ADMIN, "encodedPassword");
        UserDTO user2 = new UserDTO(2L, "jane_doe", "jane@example.com", Role.USER, "encodedPassword");
        List<UserDTO> users = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
        verify(userService).getAllUsers();
    }

    @Test
    public void getUserById_ShouldReturnUser() {

        UserDTO userDTO = new UserDTO(1L, "john_doe", "john@example.com", Role.ADMIN, "encodedPassword");

        when(userService.getUserById(anyLong())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
        verify(userService).getUserById(1L);
    }

    @Test
    public void updateUser_ShouldReturnUpdatedUser() {

        UserCreateDTO userCreateDTO = new UserCreateDTO("john_doe_updated", "john_updated@example.com", Role.ADMIN, "password123");
        UserDTO updatedUserDTO = new UserDTO(1L, "john_doe_updated", "john_updated@example.com", Role.ADMIN, "encodedPassword");

        when(userService.updateUser(anyLong(), any(UserCreateDTO.class))).thenReturn(updatedUserDTO);

        ResponseEntity<UserDTO> response = userController.updateUser(1L, userCreateDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUserDTO, response.getBody());
        verify(userService).updateUser(1L, userCreateDTO);
    }

    @Test
    public void deleteUser_ShouldReturnNoContent() {

        doNothing().when(userService).deleteUser(anyLong());

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService).deleteUser(1L);
    }
}
