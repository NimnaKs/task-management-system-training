package lk.rumex.taskmanagerapp.entity;

import jakarta.persistence.*;
import lk.rumex.taskmanagerapp.Enum.Role;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private String username;

    @Column(unique=true, nullable=false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role;
}

