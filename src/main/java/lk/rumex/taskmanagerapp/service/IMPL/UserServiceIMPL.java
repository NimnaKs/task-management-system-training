package lk.rumex.taskmanagerapp.service.IMPL;

import lk.rumex.taskmanagerapp.conversion.ConversionData;
import lk.rumex.taskmanagerapp.dto.UserCreateDTO;
import lk.rumex.taskmanagerapp.dto.UserDTO;
import lk.rumex.taskmanagerapp.entity.User;
import lk.rumex.taskmanagerapp.exception.ResourceNotFoundException;
import lk.rumex.taskmanagerapp.repository.UserRepository;
import lk.rumex.taskmanagerapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    private final UserRepository userRepository;
    private final ConversionData conversionData;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        User user = conversionData.convertToUserEntity(Optional.ofNullable(userCreateDTO));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return conversionData.convertToUserDTO(Optional.of(savedUser));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return conversionData.getUserDTOList(userRepository.findAll());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        return conversionData.convertToUserDTO(Optional.ofNullable(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserCreateDTO userCreateDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        user.setUsername(userCreateDTO.getUsername());
        user.setEmail(userCreateDTO.getEmail());
        user.setRole(userCreateDTO.getRole());
        User updatedUser = userRepository.save(user);
        return conversionData.convertToUserDTO(Optional.of(updatedUser));
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        userRepository.delete(user);
    }
}
