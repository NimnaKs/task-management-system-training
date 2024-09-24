package lk.rumex.taskmanagerapp.controller;

import lk.rumex.taskmanagerapp.secureAndResponse.response.JwtAuthResponse;
import lk.rumex.taskmanagerapp.secureAndResponse.secure.SignIn;
import lk.rumex.taskmanagerapp.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signIn_ShouldReturnJwtAuthResponse() {

        SignIn signIn = new SignIn("john_doe", "password123");
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse("accessToken");

        when(authenticationService.signIn(any(SignIn.class))).thenReturn(jwtAuthResponse);

        ResponseEntity<JwtAuthResponse> response = authController.signIn(signIn);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jwtAuthResponse, response.getBody());
    }

    @Test
    void refresh_ShouldReturnJwtAuthResponse() {

        String refreshToken = "validRefreshToken";
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse("newAccessToken");

        when(authenticationService.refreshToken(refreshToken)).thenReturn(jwtAuthResponse);

        ResponseEntity<JwtAuthResponse> response = authController.refresh(refreshToken);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jwtAuthResponse, response.getBody());
    }
}
