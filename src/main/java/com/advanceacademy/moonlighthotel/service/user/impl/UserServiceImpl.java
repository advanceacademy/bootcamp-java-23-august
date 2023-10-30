package com.advanceacademy.moonlighthotel.service.user.impl;

import com.advanceacademy.moonlighthotel.converter.user.UserConverter;
import com.advanceacademy.moonlighthotel.dto.user.UpdateUserInfoRequest;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.payload.request.SignupRequest;

import com.advanceacademy.moonlighthotel.payload.response.UserInfoResponse;
import com.advanceacademy.moonlighthotel.repository.user.UserRepository;
import com.advanceacademy.moonlighthotel.security.AuthenticationService;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    @Autowired
    private final AuthenticationService authenticationService;

    private final AuthenticationManager manager;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserInfoResponse createNewUser(SignupRequest request) {
        User user = userConverter.toUser(request);

        User savedUser = userRepository.save(user);

        return userConverter.toResponse(savedUser);
    }


    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with email %s not found", email)));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUserByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("There is no users with that first name %s", firstName)));

    }

    @Override
    public List<User> getUserByLastName(String lastName) {
        return userRepository.findByLastName(lastName)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("There is no users with that first name %s", lastName)));
    }

    @Override
    public List<User> getUserByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with first name %s and laslt name %s not found", firstName, lastName)));
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with phone number %s not found", phoneNumber)));
    }

    public UserInfoResponse updateUser(Long userId, UpdateUserInfoRequest updateUserInfoRequest) {
        User updatedUser = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException(String.format("There is no user matching id %s.", userId)));
        if (authenticationService.getLoggedUserEmail().equals(updatedUser.getEmail())) {
            updatedUser.setFirstName(updateUserInfoRequest.getFirstName());
            updatedUser.setLastName(updateUserInfoRequest.getLastName());
            updatedUser.setPhoneNumber(updateUserInfoRequest.getPhoneNumber());
        }
        else {
            throw new AccessDeniedException("You do not have permission to update this user's details.");
        }
        return userConverter.toResponse(userRepository.save(updatedUser));
    }


    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


    @Override
    public Optional<User> getUserByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public String getAuthUserEmail() {
        UserDetails authUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String authUserEmail = authUser.getUsername();
        return authUserEmail;

    }


}
