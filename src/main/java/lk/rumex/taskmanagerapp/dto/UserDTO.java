package lk.rumex.taskmanagerapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lk.rumex.taskmanagerapp.Enum.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;

    @Schema(description = "Username of the user", example = "john_doe", required = true)
    private String username;

    @Schema(description = "Email address of the user", example = "john.doe@example.com", required = true)
    private String email;

    @Schema(description = "Role assigned to the user", example = "ADMIN", required = true)
    private Role role;

    @Schema(description = "Password of the user", example = "securepassword", required = true)
    private String password;
}
