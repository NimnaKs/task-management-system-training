package lk.rumex.taskmanagerapp.service;

import lk.rumex.taskmanagerapp.secureAndResponse.response.JwtAuthResponse;
import lk.rumex.taskmanagerapp.secureAndResponse.secure.SignIn;

public interface AuthenticationService {
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse refreshToken(String refreshToken);
}
