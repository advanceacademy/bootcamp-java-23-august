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


        UserRole adminUserRole = UserRole.builder().userRole("ROLE_ADMIN").build();
        userRoleService.isRoleCorrect(adminUserRole);
        if (userRoleService.isRoleCorrect(adminUserRole)) {
            userRoleService.createUserRole(adminUserRole);
        }


        UserRole userUserRole = UserRole.builder().userRole("ROLE_USER").build();
        boolean isRoleCorrect = userRoleService.isRoleCorrect(adminUserRole);
        if (userRoleService.isRoleCorrect(adminUserRole)) {
            userRoleService.createUserRole(adminUserRole);
        }


        User adminUser = User.builder().firstName("Admin").lastName("User").email("admin@example.com").phoneNumber("1234567890").password(bCryptPasswordEncoder.encode("Ad!min123")).userRole(adminUserRole).build();

        Optional<User> foundUser = userRepository.findByEmail(adminUser.getEmail());

        if (foundUser.isEmpty()) {
            userRepository.save(adminUser);
        }


    }
}
