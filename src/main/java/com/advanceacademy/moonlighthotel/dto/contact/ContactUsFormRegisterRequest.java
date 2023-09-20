package com.advanceacademy.moonlighthotel.dto.contact;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ContactUsFormRegisterRequest {
    @NotNull
    @Size(min = 2, max = 255, message = "User name must be between {min} and {max} characters")
    private String userName;

    @NotNull
    @Size(min = 5, max = 255, message = "Email length must be between {min} and {max} characters")
    @Email(regexp = "^[^ @]+@[^ @]+\\.[^ @]+$", message = "Invalid email address format")
    private String userEmail;

    @NotNull
    @Size(max = 15, message = "Phone number length must be at most {max} characters")
    @Pattern(regexp = "^(?:00|\\+)[0-9\\s.-]{6,15}$", message = "Invalid phone number format")
    private String userPhone;

    @NotNull
    @NotBlank(message = "Message may not be blank")
    @Column(columnDefinition="TEXT")
    private String userMessage;

}





