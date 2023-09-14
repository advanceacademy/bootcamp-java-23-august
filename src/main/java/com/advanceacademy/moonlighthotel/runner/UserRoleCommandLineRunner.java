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
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
       //CHECK THE ROLE_ADMIN IF EXIST
        UserRole adminUserRole = userRoleService.findByUserRoleName("ROLE_ADMIN");
        if (adminUserRole == null) {
            adminUserRole = UserRole
                    .builder()
                    .userRole("ROLE_ADMIN")
                    .build();
            userRoleService.createUserRole(adminUserRole);
        }

       //CHECK THE ROLE_USER IF EXIST
        UserRole userUserRole = userRoleService.findByUserRoleName("ROLE_USER");
        if (userUserRole == null) {
            userUserRole = UserRole.builder()
                    .userRole("ROLE_USER")
                    .build();
            userRoleService.createUserRole(userUserRole);
        }

      //  Check if admin user with email "admin@example.com" exists
        Optional<User> foundUser = userRepository.findByEmail("admin@example.com");

        if (foundUser.isEmpty()) {
            adminUserRole = userRoleService.findByUserRoleName("ROLE_ADMIN");
            if (adminUserRole != null) {
                User adminUser = User.builder()
                        .firstName("Admin")
                        .lastName("User")
                        .email("admin@example.com")
                        .phoneNumber("1234567890")
                        .password(bCryptPasswordEncoder.encode("Ad!min123"))
                        .userRole(adminUserRole) // Set the user role
                        .build();
                userRepository.save(adminUser);
            }
        }
    }
}
