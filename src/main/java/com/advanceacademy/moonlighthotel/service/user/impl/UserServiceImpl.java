package com.advanceacademy.moonlighthotel.service.user.impl;

import com.advanceacademy.moonlighthotel.converter.user.UserConverter;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.payload.request.LoginRequest;
import com.advanceacademy.moonlighthotel.payload.request.SignupRequest;
import com.advanceacademy.moonlighthotel.payload.response.JwtResponse;
import com.advanceacademy.moonlighthotel.payload.response.UserInfoResponse;
import com.advanceacademy.moonlighthotel.repository.user.UserRepository;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return null;
    }

    @Override
    public List<User> getUserByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with first name %s not found", firstName)));
    }

    @Override
    public List<User> getUserByLastName(String lastName) {
        return userRepository.findByLastName(lastName)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with last name %s not found", lastName)));
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with phone number %s not found", phoneNumber)));
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

    @Override
    public List<User> searchUsers(Long id, String email, String firstName, String lastName, String phoneNumber) {
        return userRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), id));
            }
            if (email != null) {
                predicates.add(criteriaBuilder.equal(root.get("email"), email));
            }
            if (firstName != null) {
                predicates.add(criteriaBuilder.equal(root.get("firstName"), firstName));
            }
            if (lastName != null) {
                predicates.add(criteriaBuilder.equal(root.get("lastName"), lastName));
            }
            if (phoneNumber != null) {
                predicates.add(criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
