package com.github.jbence1994.webshop.user;

import org.springframework.stereotype.Component;

@Component
public class ResetPasswordRequestSanitizer {

    public ResetPasswordRequest sanitize(ResetPasswordRequest request) {
        return new ResetPasswordRequest(
                request.recoveryCode().trim(),
                request.newPassword().trim(),
                request.confirmNewPassword().trim()
        );
    }
}
