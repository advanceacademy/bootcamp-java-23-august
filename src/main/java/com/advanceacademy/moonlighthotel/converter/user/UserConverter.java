package com.advanceacademy.moonlighthotel.converter.user;

import com.advanceacademy.moonlighthotel.dto.user.UserResponse;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.entity.user.UserRole;
import com.advanceacademy.moonlighthotel.payload.request.SignupRequest;
import com.advanceacademy.moonlighthotel.payload.response.UserInfoResponse;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User toUser(SignupRequest request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .build();
    }

    public UserInfoResponse toResponse(User savedUser) {
        return new UserInfoResponse(
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getPhoneNumber());
    }

    public UserResponse toUserResponse(User response) {
        UserRole userRole = UserRole.builder()
                .id(response.getId())
                .userRole(response.getUserRole().getUserRole())
                .build();

        return UserResponse.builder()
                .userId(response.getId())
                .firstName(response.getFirstName())
                .lastName(response.getLastName())
                .email(response.getEmail())
                .phoneNumber(response.getPhoneNumber())
                .createdDate(response.getCreatedDate())
                .userRoleId(userRole)
                .build();
    }
}
