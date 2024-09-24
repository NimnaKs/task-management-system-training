package lk.rumex.taskmanagerapp.controller;

import io.swagger.v3.oas.annotations.media.Content;
import lk.rumex.taskmanagerapp.secureAndResponse.response.JwtAuthResponse;
import lk.rumex.taskmanagerapp.secureAndResponse.secure.SignIn;
import lk.rumex.taskmanagerapp.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Sign in a user", description = "Authenticate a user with username and password to receive a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully signed in", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid credentials"),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid input")
    })
    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthResponse> signIn(@Parameter(description = "Sign in request object containing username and password") @RequestBody SignIn signIn) {
        return ResponseEntity.ok(authenticationService.signIn(signIn));
    }

    @Operation(summary = "Refresh JWT token", description = "Refresh the access token using a valid refresh token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully refreshed token", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid refresh token"),
            @ApiResponse(responseCode = "400", description = "Bad request - Missing or invalid refresh token")
    })
    @GetMapping("/refresh")
    public ResponseEntity<JwtAuthResponse> refresh(
            @Parameter(description = "The refresh token") @RequestParam("refreshToken") String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}

