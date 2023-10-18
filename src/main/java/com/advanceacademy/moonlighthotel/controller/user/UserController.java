package com.advanceacademy.moonlighthotel.controller.user;


import com.advanceacademy.moonlighthotel.converter.user.UserConverter;
import com.advanceacademy.moonlighthotel.dto.user.UserResponse;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    private final UserConverter userConverter;

    @GetMapping("/admin/users/get-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers (){
        List<User> foundUsers = userService.getAllUsers();
        if(foundUsers.isEmpty()){
            ResponseEntity.notFound().build();
        }
        List<UserResponse> userResponses = foundUsers.stream().map(userConverter::toUserResponse).collect(Collectors.toList());
        return ResponseEntity.ok(userResponses);
    }
}
