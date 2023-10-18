package com.advanceacademy.moonlighthotel.service.user.impl;

import com.advanceacademy.moonlighthotel.converter.user.UserConverter;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.repository.user.UserRepository;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    private final AuthenticationManager manager;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //@Override
    //public UserInfoResponse createNewUser(SignupRequest request) {
    //    User user = userConverter.toUser(request);
//
    //    User savedUser = userRepository.save(user);
//
    //    return userConverter.toResponse(savedUser);
    //}


    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(Long userId,User updatedUser) {
        User extantUser = userRepository.findById(userId).orElse(null);

        if (extantUser != null) {
            extantUser.setFirstName(updatedUser.getFirstName());
        }
        if (extantUser != null) {
            extantUser.setLastName(updatedUser.getLastName());
        }
        if (extantUser != null) {
            extantUser.setEmail(updatedUser.getEmail());
        }
        if (extantUser != null){
            extantUser.setPhoneNumber(updatedUser.getPhoneNumber());
        }
        if (extantUser != null){
            extantUser.setUserRole(updatedUser.getUserRole());
        }

        return userRepository.save(extantUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
