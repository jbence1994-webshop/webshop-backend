package com.github.jbence1994.webshop.user;

import org.springframework.stereotype.Component;

@Component
public class ChangePasswordRequestSanitizer {

    public ChangePasswordRequest sanitize(ChangePasswordRequest request) {
        return new ChangePasswordRequest(
                request.oldPassword().trim(),
                request.newPassword().trim(),
                request.confirmNewPassword().trim()
        );
    }
}
