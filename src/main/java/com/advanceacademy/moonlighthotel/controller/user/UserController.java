package com.advanceacademy.moonlighthotel.controller.user;

import com.advanceacademy.moonlighthotel.converter.user.UserConverter;
import com.advanceacademy.moonlighthotel.dto.user.UserResponse;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping("/admin/users/search-users")
    public ResponseEntity<List<UserResponse>> searchUsers(@RequestParam(required = false) Long id,
                                                          @RequestParam(required = false) String email,
                                                          @RequestParam(required = false) String firstName,
                                                          @RequestParam(required = false) String lastName,
                                                          @RequestParam(required = false) String phoneNumber){
        List<User> users = userService.searchUsers(id, email, firstName, lastName, phoneNumber);
        List<UserResponse> userResponses = users.stream()
                .map(userConverter::toUserResponse)
                .collect(Collectors.toList());

        if (userResponses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userResponses);
    }

}
