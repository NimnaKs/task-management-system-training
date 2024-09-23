package lk.rumex.taskmanagerapp.service.IMPL;

import lk.rumex.taskmanagerapp.entity.User;
import lk.rumex.taskmanagerapp.exception.ResourceNotFoundException;
import lk.rumex.taskmanagerapp.repository.UserRepository;
import lk.rumex.taskmanagerapp.secureAndResponse.response.JwtAuthResponse;
import lk.rumex.taskmanagerapp.secureAndResponse.secure.SignIn;
import lk.rumex.taskmanagerapp.service.AuthenticationService;
import lk.rumex.taskmanagerapp.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceIMPL implements AuthenticationService {

    private final UserRepository userDao;
    private final JwtService jwtService;

    @Override
    public JwtAuthResponse signIn(SignIn signIn) {
        User user = userDao.findByUsername(signIn.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        var generateToken = jwtService.generateToken(user);
        return JwtAuthResponse.builder().token(generateToken).build();
    }

    @Override
    public JwtAuthResponse refreshToken(String refreshToken) {
        var userEntity = userDao
                .findByUsername(jwtService.extractUserName(refreshToken))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return JwtAuthResponse.builder().
                token(jwtService.generateToken(userEntity)).build();
    }
}