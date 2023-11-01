package com.advanceacademy.moonlighthotel.controller.user;

import com.advanceacademy.moonlighthotel.converter.user.UserConverter;
import com.advanceacademy.moonlighthotel.dto.user.UpdateUserInfoRequest;
import com.advanceacademy.moonlighthotel.dto.user.UserResponse;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.payload.response.UserInfoResponse;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
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

    @PutMapping(path = "/user/update/{id}")
    public ResponseEntity<UserInfoResponse> updateUserById(@PathVariable Long id,
                                                           @Valid @RequestBody UpdateUserInfoRequest updateUserInfoRequest){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, updateUserInfoRequest));

    }

    @GetMapping("/admin/users/search-users")
    public ResponseEntity<?> searchUsers(@RequestParam(required = false) Long id,
                                         @RequestParam(required = false) String email,
                                         @RequestParam(required = false) String firstName,
                                         @RequestParam(required = false) String lastName,
                                         @RequestParam(required = false) String phoneNumber) {
        List<User> searchResponse = new ArrayList<>();

        if (id != null) {
            // Search for a user by ID
            Optional<User> foundUserById = userService.getUserById(id);
            if (foundUserById.isPresent()) {
                User userById = foundUserById.get();

                // Check if other parameters match the user's properties
                if ((email == null || email.equals(userById.getEmail())) &&
                        (firstName == null || firstName.equals(userById.getFirstName())) &&
                        (lastName == null || lastName.equals(userById.getLastName())) &&
                        (phoneNumber == null || phoneNumber.equals(userById.getPhoneNumber()))) {
                    // If all parameters match, add the user to the results
                    searchResponse.add(userById);
                } else {
                    return ResponseEntity.badRequest().body("User with these values not found");
                }
            }
        } else if (email != null) {
            // Search for a user by email
            User foundUserByEmail = userService.findByEmail(email);
            if (foundUserByEmail != null) {
                // Check if other parameters match the user's properties
                if ((firstName == null || firstName.equals(foundUserByEmail.getFirstName()))
                        && (lastName == null || lastName.equals(foundUserByEmail.getLastName()))
                        && (phoneNumber == null || phoneNumber.equals(foundUserByEmail.getPhoneNumber()))) {
                    // If all parameters match, add the user to the results
                    searchResponse.add(foundUserByEmail);
                } else {
                    return ResponseEntity.badRequest().body("User with these values not found");
                }
            }
        } else if (phoneNumber != null) {
            // Search for a user by phoneNumber
            User foundUserByPhoneNumber = userService.getUserByPhoneNumber(phoneNumber);
            if (foundUserByPhoneNumber != null) {
                // Check if other parameters match the user's properties
                if ((firstName == null || firstName.equals(foundUserByPhoneNumber.getFirstName()))
                        && (lastName == null || lastName.equals(foundUserByPhoneNumber.getLastName()))) {
                    // If all parameters match, add the user to the results
                    searchResponse.add(foundUserByPhoneNumber);
                } else {
                    return ResponseEntity.badRequest().body("User with these values not found");
                }
            }
        } else {
//            // Neither ID, email, nor phoneNumber is provided, so perform additional checks
//            if (firstName != null) {
//                List<User> foundUsersByFirstName = userService.getUserByFirstName(firstName);
//                if (!foundUsersByFirstName.isEmpty()) {
//                    // If any matching users are found, add them to the results
//                    searchResponse.addAll(foundUsersByFirstName);
//                } else {
//                    return ResponseEntity.badRequest().body("User with these values not found");
//                }
//            }

//            // Check if only lastName is present
//            if (lastName != null) {
//                List<User> foundUsersByLastName = userService.getUserByLastName(lastName);
//                if (!foundUsersByLastName.isEmpty()) {
//                    // If any matching users are found, add them to the results
//                    searchResponse.addAll(foundUsersByLastName);
//                } else {
//                    return ResponseEntity.badRequest().body("User with these values not found");
//                }
//            }

            // Check if both firstName and lastName are present
            if (firstName != null && lastName != null) {
                List<User> foundUsersByFirstNameAndLastName = userService.getUserByFirstNameAndLastName(firstName, lastName);
                if (!foundUsersByFirstNameAndLastName.isEmpty()) {
                    // If any matching users are found, add them to the results
                    searchResponse.addAll(foundUsersByFirstNameAndLastName);
                } else {
                    return ResponseEntity.badRequest().body("User with these values not found");
                }
            }else if (firstName != null){
                List<User> foundUsersByFirstName = userService.getUserByFirstName(firstName);
                if (!foundUsersByFirstName.isEmpty()) {
                    // If any matching users are found, add them to the results
                    searchResponse.addAll(foundUsersByFirstName);
                } else {
                    return ResponseEntity.badRequest().body("User with these values not found");
                }
            }else if (lastName != null){
                List<User> foundUsersByLastName = userService.getUserByLastName(lastName);
                if (!foundUsersByLastName.isEmpty()) {
                    // If any matching users are found, add them to the results
                    searchResponse.addAll(foundUsersByLastName);
                } else {
                    return ResponseEntity.badRequest().body("User with these values not found");
                }
            }
        }

        if (searchResponse.isEmpty()) {
            // No matching users found
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(searchResponse.stream()
                .map(userConverter::toUserResponse)
                .collect(Collectors.toList()));
    }
}
