package lk.rumex.taskmanagerapp.initializer;

import lk.rumex.taskmanagerapp.Enum.Role;
import lk.rumex.taskmanagerapp.entity.User;
import lk.rumex.taskmanagerapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findByRole(Role.ADMIN).isEmpty()) {

            String password = "rumex.lk";
            User adminUser = new User();
            adminUser.setUsername("Rumex_ADMIN");
            adminUser.setEmail("admin@rumex.lk");
            adminUser.setPassword(passwordEncoder.encode(password));
            adminUser.setRole(Role.ADMIN);

            userRepository.save(adminUser);

            log.info("Admin user created successfully with username: {}, email: {}, Password: {}", adminUser.getUsername(), adminUser.getEmail(), password);
        }
    }
}
