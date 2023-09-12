package com.advanceacademy.moonlighthotel.runner;

import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.entity.user.UserRole;
import com.advanceacademy.moonlighthotel.entity.user.UserRoleAsset;
import com.advanceacademy.moonlighthotel.repository.user.AssetRepository;
import com.advanceacademy.moonlighthotel.repository.user.UserRepository;
import com.advanceacademy.moonlighthotel.repository.user.UserRoleRepository;
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
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void run(String... args) {

        UserRole adminUserRole = new UserRole();
        adminUserRole.setUserRole("ROLE_ADMIN");

        // Create and associate an asset with the admin role
        UserRoleAsset adminAsset = new UserRoleAsset();
        adminAsset.setAssetName("Admin Asset");
        adminAsset.setUserRole(adminUserRole);

        adminUserRole.getAssets().add(adminAsset);

        // Save the admin user role and asset
        userRoleRepository.save(adminUserRole);
        assetRepository.save(adminAsset);

        // Create an admin user and encode the password
        User adminUser = new User();
        adminUser.setFirstName("Admin");
        adminUser.setLastName("User");
        adminUser.setEmail("admin@example.com");
        adminUser.setPhoneNumber("1234567890");
        adminUser.setPassword(bCryptPasswordEncoder.encode("Ad!min123")); // Encrypt the password


        // Assign the admin role to the admin user
        adminUser.setUserRole(adminUserRole);

        // Save the admin user
        userRepository.save(adminUser);

    }
}
