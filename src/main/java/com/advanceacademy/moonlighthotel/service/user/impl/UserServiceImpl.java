package com.advanceacademy.moonlighthotel.service.user.impl;

import com.advanceacademy.moonlighthotel.converter.user.UserConverter;
import com.advanceacademy.moonlighthotel.dto.user.UpdateUserInfoRequest;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.payload.request.SignupRequest;
import com.advanceacademy.moonlighthotel.payload.response.UserInfoResponse;
import com.advanceacademy.moonlighthotel.repository.user.UserRepository;
import com.advanceacademy.moonlighthotel.security.AuthenticationService;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    private final AuthenticationManager manager;

    @Autowired
    private final AuthenticationService authenticationService;

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
}
