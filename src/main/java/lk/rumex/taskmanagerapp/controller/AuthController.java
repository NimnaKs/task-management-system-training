package lk.rumex.taskmanagerapp.controller;

import lk.rumex.taskmanagerapp.secureAndResponse.response.JwtAuthResponse;
import lk.rumex.taskmanagerapp.secureAndResponse.secure.SignIn;
import lk.rumex.taskmanagerapp.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignIn signIn){
        return ResponseEntity.ok(authenticationService.signIn(signIn));
    }

    @GetMapping("/refresh")
    public ResponseEntity<JwtAuthResponse> refresh(
            @RequestParam ("refreshToken") String refreshToken
    ){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}
