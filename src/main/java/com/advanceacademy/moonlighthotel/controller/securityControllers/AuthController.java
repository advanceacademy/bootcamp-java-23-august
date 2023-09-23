package com.advanceacademy.moonlighthotel.controller.securityControllers;

import com.advanceacademy.moonlighthotel.payload.request.LoginRequest;
import com.advanceacademy.moonlighthotel.payload.request.SignupRequest;
import com.advanceacademy.moonlighthotel.payload.response.JwtResponse;
import com.advanceacademy.moonlighthotel.payload.response.UserInfoResponse;
import com.advanceacademy.moonlighthotel.repository.user.UserRepository;
import com.advanceacademy.moonlighthotel.repository.user.UserRoleRepository;
import com.advanceacademy.moonlighthotel.security.jwt.JwtUtils;
import com.advanceacademy.moonlighthotel.security.services.UserDetailsServiceImpl;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserRoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Operation(summary = "Register user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserInfoResponse.class))}),
            @ApiResponse(responseCode = "409", description = "User with email already used",
                    content = @Content)})
    @PostMapping(path = "/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest request) {
        userService.createNewUser(request);

        String jwt = jwtUtils.generateTokenFromEmail(request.getEmail());

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Извличане на потребителско име и парола от loginRequest
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Извършване на аутентикация с помощта на Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        // Генериране на JWT токен
        UserDetails userDetails = userDetailsService.loadUserByEmail(loginRequest.getEmail());
        String jwt = jwtUtils.generateTokenFromEmail(userDetails.getUsername());

        // Връщане на токена като отговор
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
