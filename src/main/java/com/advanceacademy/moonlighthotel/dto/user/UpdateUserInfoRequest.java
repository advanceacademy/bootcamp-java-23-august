package com.advanceacademy.moonlighthotel.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateUserInfoRequest {
    @NotNull
    @Size(min = 2, max = 255, message = "First name length must be between {min} and {max} characters")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 255, message = "Last name length must be between {min} and {max} characters")
    private String lastName;

    @NotNull
    @Size(max = 15, message = "Phone number length must be at most {max} characters")
    @Pattern(regexp = "^(?:00|\\+)[0-9\\s.-]{6,15}$", message = "Invalid phone number format")
    private String phoneNumber;

}
