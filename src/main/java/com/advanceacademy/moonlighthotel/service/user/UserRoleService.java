package com.advanceacademy.moonlighthotel.service.user;

import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.entity.user.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserRoleService {

    UserRole createUserRole(UserRole userRole);

    Optional<UserRole> getUserRoleById(Long id);

    UserRole updateUserRole(Long userRoleId, UserRole updatedUserRole);

    void deleteUserRole(Long userRoleId);
}
