package com.github.jbence1994.webshop.user;

import org.springframework.stereotype.Component;

@Component
public class ForgotPasswordRequestSanitizer {

    public ForgotPasswordRequest sanitize(ForgotPasswordRequest request) {
        return new ForgotPasswordRequest(request.email().trim());
    }
}
