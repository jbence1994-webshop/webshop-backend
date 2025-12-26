package com.github.jbence1994.webshop.auth;

import org.springframework.stereotype.Component;

@Component
public class LoginRequestSanitizer {

    public LoginRequest sanitize(LoginRequest request) {
        var sanitizedEmail = request.getEmail().trim();
        var sanitizedPassword = request.getPassword().trim();

        return new LoginRequest(
                sanitizedEmail,
                sanitizedPassword
        );
    }
}
