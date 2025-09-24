package com.github.jbence1994.webshop.user;

import org.springframework.stereotype.Component;

@Component
public class ChangePasswordRequestSanitizer {

    public ChangePasswordRequest sanitize(ChangePasswordRequest request) {
        var sanitizedOldPassword = request.getOldPassword().trim();
        var sanitizedNewPassword = request.getNewPassword().trim();
        var sanitizedConfirmNewPassword = request.getConfirmNewPassword().trim();

        return new ChangePasswordRequest(
                sanitizedOldPassword,
                sanitizedNewPassword,
                sanitizedConfirmNewPassword
        );
    }
}
