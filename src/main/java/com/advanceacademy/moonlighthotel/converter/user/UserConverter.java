package com.advanceacademy.moonlighthotel.converter.user;

import com.advanceacademy.moonlighthotel.dto.user.UserResponse;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.payload.request.SignupRequest;
import com.advanceacademy.moonlighthotel.payload.response.UserInfoResponse;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class UserConverter {

    public User toUser(SignupRequest request){
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                //.phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .build();
    }

    public UserInfoResponse toResponse(User savedUser){
        return new UserInfoResponse(
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getPhoneNumber(),
                savedUser.getUserRole().getUserRole());
    }

    public UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .createdDate(user.getCreatedDate())
                .userRole(user.getUserRole())
                .build();
    }
}
