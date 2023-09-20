package com.advanceacademy.moonlighthotel.service.user;

import com.advanceacademy.moonlighthotel.entity.user.UserRole;
import com.advanceacademy.moonlighthotel.payload.request.SignupRequest;
import com.advanceacademy.moonlighthotel.payload.response.UserInfoResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRoleService {

    UserRole createUserRole(UserRole userRole);

    UserInfoResponse createNewUser(SignupRequest request);

    Optional<UserRole> getUserRoleById(Long id);

    UserRole updateUserRole(Long userRoleId, UserRole updatedUserRole);

    void deleteUserRole(Long userRoleId);

    UserRole findByUserRoleName(String role_admin);
}
