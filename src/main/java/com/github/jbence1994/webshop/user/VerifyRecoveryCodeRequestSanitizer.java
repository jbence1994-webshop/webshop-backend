package com.github.jbence1994.webshop.user;

import org.springframework.stereotype.Component;

@Component
public class VerifyRecoveryCodeRequestSanitizer {

    public VerifyRecoveryCodeRequest sanitize(VerifyRecoveryCodeRequest request) {
        return new VerifyRecoveryCodeRequest(
                request.email().trim(),
                request.recoveryCode().trim()
        );
    }
}
