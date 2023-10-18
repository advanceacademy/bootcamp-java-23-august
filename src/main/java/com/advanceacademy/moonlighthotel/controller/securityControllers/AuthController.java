package com.advanceacademy.moonlighthotel.controller.securityControllers;

import com.advanceacademy.moonlighthotel.dto.password.ResetPasswordRequest;
import com.advanceacademy.moonlighthotel.payload.request.LoginRequest;
import com.advanceacademy.moonlighthotel.payload.request.SignupRequest;
import com.advanceacademy.moonlighthotel.payload.response.AuthResponse;
import com.advanceacademy.moonlighthotel.payload.response.UserInfoResponse;
import com.advanceacademy.moonlighthotel.repository.user.UserRepository;
import com.advanceacademy.moonlighthotel.repository.user.UserRoleRepository;
import com.advanceacademy.moonlighthotel.security.AuthenticationService;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authService;


    @PostMapping(path = "/register")
    @Operation(
            description = "Register a user",
            summary = "User Registration",
            responses = {
                    @ApiResponse(
                            description = "User registered successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponse.class)
                            )
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User registration request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SignupRequest.class),
                            examples = @ExampleObject(value =
                                    "{" +
                                            "  \"firstName\": \"Atanas\"," +
                                            "  \"lastName\": \"Krastev\"," +
                                            "  \"email\": \"nasko@gmail.com\"," +
                                            "  \"phoneNumber\": \"+359 894555666\"," +
                                            "  \"password\": \"passWord12@\"" +
                                            "}"
                            )
                    )
            ),
            operationId = "registerUser",
            tags = {"User Authentication"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody SignupRequest request) {

        return ResponseEntity.ok(authService.register(request));
    }


    @PostMapping("/login")
    @Operation(
            description = "Log in a user",
            summary = "User Login",
            responses = {
                    @ApiResponse(
                            description = "User logged in successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponse.class)
                            )
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User login request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginRequest.class),
                            examples = @ExampleObject(value =
                                    "{" +
                                    "  \"email\": \"nasko@gmail.com\"," +
                                    "  \"password\": \"passWord12@\"" +
                                    "}"
                            )
                    )
            ),
            operationId = "loginUser",
            tags = {"User Authentication"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRequest) {

      return ResponseEntity.ok(authService.login(loginRequest));

    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
        authService.resetAndEmailPassword(resetPasswordRequest);
        return ResponseEntity.ok("Your password has been reset. A new password was sent to your provided email address. For security reasons, we recommend changing that password with a new one.");
    }

}
