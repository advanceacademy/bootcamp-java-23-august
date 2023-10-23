package com.advanceacademy.moonlighthotel.controller.user;

import com.advanceacademy.moonlighthotel.dto.user.UpdateUserInfoRequest;
import com.advanceacademy.moonlighthotel.payload.response.UserInfoResponse;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(path = "/user/update/{id}")
    public ResponseEntity<UserInfoResponse> updateUserById(@PathVariable Long id,
                                                           @Valid @RequestBody UpdateUserInfoRequest updateUserInfoRequest){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, updateUserInfoRequest));

    }

}
