package com.advanceacademy.moonlighthotel.serviceImpl.user;

import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.entity.user.UserRole;
import com.advanceacademy.moonlighthotel.repository.user.UserRepository;
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
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User savedUser = userService.updateUser(1L, user);

        Assertions.assertThat(savedUser).isNotNull();
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
