package com.advanceacademy.moonlighthotel.dto.user;

import com.advanceacademy.moonlighthotel.entity.user.UserRole;
import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserResponse {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate createdDate;
    private UserRole userRoleId;
}
