package com.advanceacademy.moonlighthotel.security.services;

import com.advanceacademy.moonlighthotel.entity.user.User;

public interface EmailService {
    void sendPasswordResetEmail(User user, String newPassword);
}
