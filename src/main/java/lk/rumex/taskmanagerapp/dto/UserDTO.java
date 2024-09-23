package lk.rumex.taskmanagerapp.dto;
import lk.rumex.taskmanagerapp.Enum.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Role role;
}
