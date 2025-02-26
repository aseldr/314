package asel.java.spring.security;

import asel.java.spring.model.Role;
import asel.java.spring.model.User;
import asel.java.spring.repository.RoleRepository;
import asel.java.spring.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
public class Init {
    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);

            User admin = new User();
            admin.setFirstName("admin");
            admin.setLastName("lastadmin");
            admin.setAge((byte) 19);
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEmail("admin@gmail.com");
            admin.setRoles(new HashSet<>(Arrays.asList(adminRole, userRole)));
            userRepository.save(admin);

            User user = new User();
            user.setFirstName("user");
            user.setLastName("lastuser");
            user.setAge((byte) 20);
            user.setPassword(passwordEncoder.encode("user"));
            user.setEmail("user@mail.ru");
            user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
            userRepository.save(user);
        };
    }
}