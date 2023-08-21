package com.advanceacademy.moonlighthotel.entities;

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

    @Column(name = "user_name")
    @NotNull
    @Size(min = 2, max = 50, message = "User name must be between {min} and {max} characters")
    private String userName;


    @NotNull
    @Email(regexp = "^[^ @]+@[^ @]+\\.[^ @]+$", message = "Invalid email address format")
    @Column(name = "user_email")
    private String userEmail;

    @NotNull
    @Pattern(regexp = "^(?:00|\\+)[0-9\\s.-]{6,20}$", message = "Invalid phone number format")
    @Column(name = "user_phone_number", length = 30)
    private String userPhone;

    @NotNull
    @NotBlank(message = "Message may not be blank")
    private String userMessage;

}
