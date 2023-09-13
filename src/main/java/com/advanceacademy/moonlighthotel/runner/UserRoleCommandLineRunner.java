package com.advanceacademy.moonlighthotel.runner;

import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.entity.user.UserRole;
import com.advanceacademy.moonlighthotel.repository.user.UserRepository;
import com.advanceacademy.moonlighthotel.repository.user.UserRoleRepository;
import com.advanceacademy.moonlighthotel.service.user.UserRoleService;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserRoleCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {

        UserRole adminUserRole = userRoleService.findByUserRoleName("ROLE_ADMIN");

        if (adminUserRole == null) {
            // Ако ролята не съществува, създайте я и запишете в базата данни
            adminUserRole = UserRole.builder().userRole("ROLE_ADMIN").build();
            userRoleService.createUserRole(adminUserRole);
        }


        // Проверете дали ролята "ROLE_USER" вече съществува
        UserRole userUserRole = userRoleService.findByUserRoleName("ROLE_USER");

        if (userUserRole == null) {
            // Ако ролята не съществува, създайте я и запишете в базата данни
            userUserRole = UserRole.builder().userRole("ROLE_USER").build();
            userRoleService.createUserRole(userUserRole);
        }


        User adminUser = User.builder()
                .firstName("Admin")
                .lastName("User")
                .email("admin@example.com")
                .phoneNumber("1234567890")
                .password(bCryptPasswordEncoder.encode("Ad!min123"))
                .build();


        Optional<User> foundUser = userRepository
                .findByEmail(adminUser.getEmail());

        if (foundUser.isEmpty()) {
            // Извлечете ролята "ROLE_ADMIN" от базата данни
            UserRole adminUserRoleTwo = userRoleService.findByUserRoleName("ROLE_ADMIN");

            if (adminUserRoleTwo != null) {
                // Свържете потребителя с ролята "ROLE_ADMIN"
                adminUser.setUserRole(adminUserRoleTwo);
            }

            userRepository.save(adminUser);

        }
    }
}
