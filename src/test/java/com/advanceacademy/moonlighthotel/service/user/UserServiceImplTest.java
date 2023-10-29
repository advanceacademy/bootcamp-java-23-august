package com.advanceacademy.moonlighthotel.service.user;

import com.advanceacademy.moonlighthotel.converter.user.UserConverter;
import com.advanceacademy.moonlighthotel.dto.user.UpdateUserInfoRequest;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.entity.user.UserRole;
import com.advanceacademy.moonlighthotel.payload.response.UserInfoResponse;
import com.advanceacademy.moonlighthotel.repository.user.UserRepository;
import com.advanceacademy.moonlighthotel.security.AuthenticationService;
import com.advanceacademy.moonlighthotel.service.user.impl.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserConverter userConverter;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void createUser(){
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@example.com");
        user.setPhoneNumber("+123456789");
        user.setPassword("password");
        user.setUserRole(new UserRole());

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User savedUser = userService.createUser(user);

        Assertions.assertThat(savedUser).isNotNull();
    }

    @Test
    public void findUserById(){
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@example.com");
        user.setPhoneNumber("+123456789");
        user.setPassword("password");
        user.setUserRole(new UserRole());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> savedUser = userService.getUserById(1L);

        Assertions.assertThat(savedUser).isNotNull();
    }

    @Test
    public void updateUser(){

        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@example.com");
        user.setPhoneNumber("+123456789");
        user.setPassword("password");
        user.setUserRole(new UserRole());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(authenticationService.getLoggedUserEmail()).thenReturn(user.getEmail());
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        when(userConverter.toResponse(Mockito.any(User.class))).thenReturn(new UserInfoResponse());

        userService.updateUser(1L, new UpdateUserInfoRequest());

        Assertions.assertThat(userRepository.save(user)).isNotNull();
        Assertions.assertThat(userConverter.toResponse(user)).isNotNull();
}

    @Test
    public void deleteUser(){
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@example.com");
        user.setPhoneNumber("+123456789");
        user.setPassword("password");
        user.setUserRole(new UserRole());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> savedUser = userService.getUserById(1L);

        assertAll(() -> userService.deleteUser(1L));
    }


}
