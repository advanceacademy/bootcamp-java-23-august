package com.advanceacademy.moonlighthotel.service.user;

import com.advanceacademy.moonlighthotel.dto.user.UpdateUserInfoRequest;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.payload.request.SignupRequest;
import com.advanceacademy.moonlighthotel.payload.response.UserInfoResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public interface UserService {

    User createUser(User user);

    UserInfoResponse createNewUser(SignupRequest request);//This method is for the controller

    Optional<User> getUserById(Long id);

    User findByEmail(String email);

    List<User> getUserByFirstName(String firstName);

    List<User> getUserByLastName(String lastName);

    User getUserByPhoneNumber(String phoneNumber);

    UserInfoResponse updateUser(Long userId, UpdateUserInfoRequest updateUserInfoRequest);

    void deleteUser(Long userId);

    Optional<User> getUserByUsername(String username);

    String getAuthUserEmail();



}
