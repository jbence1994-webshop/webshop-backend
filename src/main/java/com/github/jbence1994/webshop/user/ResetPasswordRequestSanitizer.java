package com.github.jbence1994.webshop.user;

import org.springframework.stereotype.Component;

@Component
public class ResetPasswordRequestSanitizer {

    public ResetPasswordRequest sanitize(ResetPasswordRequest request) {
        var sanitizedTemporaryPassword = request.getTemporaryPassword().trim();
        var sanitizedNewPassword = request.getNewPassword().trim();
        var sanitizedConfirmNewPassword = request.getConfirmNewPassword().trim();

        return new ResetPasswordRequest(
                sanitizedTemporaryPassword,
                sanitizedNewPassword,
                sanitizedConfirmNewPassword
        );
    }
}
