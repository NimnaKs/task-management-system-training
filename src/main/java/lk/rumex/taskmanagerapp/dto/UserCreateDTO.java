package lk.rumex.taskmanagerapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lk.rumex.taskmanagerapp.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {

    @Schema(description = "Username of the user", example = "john_doe", required = true)
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @Schema(description = "Email of the user", example = "john_doe@example.com", required = true)
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(description = "Role of the user", example = "ADMIN", required = true)
    @NotNull(message = "Role cannot be null")
    private Role role;

    @Schema(description = "Password of the user", example = "strong_password123", required = true)
    @NotNull(message = "Password cannot be null")
    private String password;
}
