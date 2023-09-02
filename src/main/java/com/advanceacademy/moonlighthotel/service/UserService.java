package com.advanceacademy.moonlighthotel.service;

import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.entity.user.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface UserService {

    User createUser(User user);

    Optional<User> getUserById(Long id);

    User updateUser(Long userId, User updatedUser);

    void deleteUser(Long userId);
}
