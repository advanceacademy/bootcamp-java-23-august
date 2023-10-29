package com.advanceacademy.moonlighthotel.dto.user;

import com.advanceacademy.moonlighthotel.entity.user.UserRole;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate createdDate;
    private UserRole userRole;
}
