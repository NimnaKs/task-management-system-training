package lk.rumex.taskmanagerapp.secureAndResponse.secure;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignIn {

    @Schema(description = "Username of the user attempting to sign in", example = "john_doe", required = true)
    private String username;

    @Schema(description = "Password of the user attempting to sign in", example = "password123", required = true)
    private String password;
}
