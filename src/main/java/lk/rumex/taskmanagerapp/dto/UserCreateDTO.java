package lk.rumex.taskmanagerapp.dto;

import lk.rumex.taskmanagerapp.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {
    private String username;
    private String email;
    private Role role;
}