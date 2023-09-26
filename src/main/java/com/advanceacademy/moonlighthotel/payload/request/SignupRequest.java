package com.advanceacademy.moonlighthotel.payload.request;

import com.advanceacademy.moonlighthotel.entity.user.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SignupRequest {

    @NotBlank
    @Size(min = 2, max = 255, message = "First name length must be between {min} and {max} characters")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 255, message = "Last name length must be between {min} and {max} characters")
    private String lastName;

    @NotBlank
    @Size(min = 5, max = 255, message = "Email must be between {min} and {max} characters")
    @Email(regexp = "^[^ @]+@[^ @]+\\.[^ @]+$", message = "Email must be valid")
    private String email;

    //@Size(max = 15, message = "Phone number length must be at most {max} characters")
    //@Pattern(regexp = "^(\\\\+|00)[0-9-]{1,15}\"", message = "Invalid phone number format")
    // Checks that the phone number starts with either "+" or "00" followed by up to 15 digits or hyphens.
    //private String phoneNumber;

    @NotBlank
    @Size(min = 8, max = 255, message = "Password must be between {min} and {max} characters")
    @Pattern(regexp = "^.*(?=.{8,})(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$")
    private String password;

}
