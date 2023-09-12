package com.advanceacademy.moonlighthotel.runner;

import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.entity.user.UserRole;
import com.advanceacademy.moonlighthotel.repository.user.UserRoleRepository;
import com.advanceacademy.moonlighthotel.service.user.UserRoleService;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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
    private UserRoleRepository userRoleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // Create and save the roles using the builder pattern
        UserRole adminUserRole = UserRole.builder()
                .userRole("ROLE_ADMIN")
                .build();
                 adminUserRole = userRoleService.createUserRole(adminUserRole);


        UserRole userUserRole = UserRole.builder()
                .userRole("ROLE_USER")
                .build();
        userUserRole = userRoleService.createUserRole(userUserRole);


        // Create an admin user using the builder pattern
        User adminUser = User.builder()
                .firstName("Admin")
                .lastName("User")
                .email("admin@example.com")
                .phoneNumber("1234567890")
                .password(bCryptPasswordEncoder.encode("Ad!min123"))
                .userRole(adminUserRole)
                .build();

        // Save the admin user
        userService.createUser(adminUser);

    }
}
