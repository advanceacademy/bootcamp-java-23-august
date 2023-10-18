package com.advanceacademy.moonlighthotel.converter.user;

import com.advanceacademy.moonlighthotel.dto.user.UserResponse;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.entity.user.UserRole;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    private final UserService userService;

    @Autowired
    public UserConverter(UserService userService) {
        this.userService = userService;
    }

    public UserResponse toUserResponse(User response){
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
