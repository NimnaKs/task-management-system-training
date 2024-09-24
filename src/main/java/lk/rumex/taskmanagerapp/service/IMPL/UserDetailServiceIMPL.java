package lk.rumex.taskmanagerapp.service.IMPL;

import lk.rumex.taskmanagerapp.exception.ResourceNotFoundException;
import lk.rumex.taskmanagerapp.repository.UserRepository;
import lk.rumex.taskmanagerapp.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceIMPL implements UserDetailService {
    private final UserRepository userRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return username ->
                userRepository.findByUsername(username).
                        orElseThrow(()->new ResourceNotFoundException("User Not Found"));
    }
}
