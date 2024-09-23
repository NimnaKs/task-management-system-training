package lk.rumex.taskmanagerapp.service.IMPL;

import lk.rumex.taskmanagerapp.repository.UserRepository;
import lk.rumex.taskmanagerapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    private UserRepository userRepository;

}
