package entities.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "first_name", nullable = false)
    @Size(min = 2, max = 255, message = "First name length must be between {min} and {max} characters")
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    @Size(min = 2, max = 255, message = "Last name length must be between {min} and {max} characters")
    private String lastName;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    @Size(min = 5, max = 255, message = "Email must be between {min} and {max} characters")
    @Email(regexp = "^[^ @]+@[^ @]+\\.[^ @]+$", message = "Email must be valid") // Validates that the email format is correct according to general email conventions.
    private String email;

    @NotNull
    @Column(name = "phone_number", nullable = false)
    @Size(max = 15, message = "Phone number length must be at most {max} characters")
    @Pattern(regexp = "^(\\\\+|00)[0-9-]{1,15}\"", message = "Invalid phone number format") // Checks that the phone number starts with either "+" or "00" followed by up to 15 digits or hyphens.
    private String phoneNumber;

    @NotNull
    @Column(name = "password")
    @Size(min = 8, max = 255, message = "Password must be between {min} and {max} characters")
    @Pattern(regexp = "^.*(?=.{8,})(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$") // This regular expression enforces the following password requirements: At least 8 characters in length, contains at least one digit, contains at least one lowercase letter, Contains at least one uppercase letter, Contains at least one special character from the set @#$%^&+=
    private String password;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDate createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_role_id")
    private UserRole userRole;
}
