package com.advanceacademy.moonlighthotel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "contact_us")
public class ContactUsForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotNull
    @Column(name = "user_name")
    @Size(min = 2, max = 255, message = "User name must be between {min} and {max} characters")
    private String userName;

    @NotNull
    @Column(name = "user_email")
    @Size(min = 5, max = 255, message = "Email length must be between {min} and {max} characters")
    @Email(regexp = "^[^ @]+@[^ @]+\\.[^ @]+$", message = "Invalid email address format")
    private String userEmail;

    @NotNull
    @Column(name = "user_phone_number")
    @Size(max = 15, message = "Phone number length must be at most {max} characters")
    @Pattern(regexp = "^(?:00|\\+)[0-9\\s.-]{3,15}$", message = "Invalid phone number format")
    private String userPhone;

    @NotNull
    @NotBlank(message = "Message may not be blank")
    @Column(name = "user_message", columnDefinition="TEXT")
    private String userMessage;

}