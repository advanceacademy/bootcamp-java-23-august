package com.advanceacademy.moonlighthotel.controller.securityControllers;

import com.advanceacademy.moonlighthotel.dto.user.UpdatePasswordRequest;
import com.advanceacademy.moonlighthotel.security.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UpdatePasswordController {
    private final AuthenticationService authService;

    @Autowired
    public UpdatePasswordController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@Valid @RequestBody UpdatePasswordRequest updatePasswordRequest){

        authService.updatePassword(updatePasswordRequest);

        return ResponseEntity.ok("Your password has been updated.");
    }

}
