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
            adminUserRole = UserRole.builder().userRole("ROLE_ADMIN").build();
            userRoleService.createUserRole(adminUserRole);
        }



        UserRole userUserRole = userRoleService.findByUserRoleName("ROLE_USER");

        if (userUserRole == null) {
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
            UserRole adminUserRoleTwo = userRoleService.findByUserRoleName("ROLE_ADMIN");

            if (adminUserRole != null) {

                adminUser.setUserRole(adminUserRole);
            }

            userRepository.save(adminUser);

        }
    }
}
