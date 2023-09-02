package com.advanceacademy.moonlighthotel.serviceImpl.user;


import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.entity.user.UserRole;
import com.advanceacademy.moonlighthotel.repository.user.UserRoleRepository;
import com.advanceacademy.moonlighthotel.service.impl.UserRoleServiceImpl;
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
public class UserRoleServiceImplTest {
    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private UserRoleServiceImpl userRoleService;


    @Test
    public void createUserRole(){
        UserRole userRole = new UserRole();
        userRole.setId(1L);
        userRole.setUserRole("Standard");

        when(userRoleRepository.save(Mockito.any(UserRole.class))).thenReturn(userRole);

        UserRole savedUserRole = userRoleService.createUserRole(userRole);

        Assertions.assertThat(savedUserRole).isNotNull();
    }

    @Test
    public void getUserRoleById(){
        UserRole userRole = new UserRole();
        userRole.setId(1L);
        userRole.setUserRole("Standard");

        when(userRoleRepository.findById(1L)).thenReturn(Optional.ofNullable(userRole));

        Optional<UserRole> savedUserRole = userRoleService.getUserRoleById(1L);

        Assertions.assertThat(savedUserRole).isNotNull();
    }

    @Test
    public void updateUserRole(){
        UserRole userRole = new UserRole();
        userRole.setId(1L);
        userRole.setUserRole("Standard");

        when(userRoleRepository.findById(1L)).thenReturn(Optional.ofNullable(userRole));
        when(userRoleRepository.save(Mockito.any(UserRole.class))).thenReturn(userRole);

        UserRole savedUserRole = userRoleService.updateUserRole(1L, userRole);

        Assertions.assertThat(savedUserRole).isNotNull();
    }

    @Test
    public void deleteUserRole(){
        UserRole userRole = new UserRole();
        userRole.setId(1L);
        userRole.setUserRole("Standard");

        when(userRoleRepository.findById(1L)).thenReturn(Optional.ofNullable(userRole));

        Optional<UserRole> savedUserRole = userRoleService.getUserRoleById(1L);

        assertAll(() -> userRoleRepository.findById(1L));
    }


}
