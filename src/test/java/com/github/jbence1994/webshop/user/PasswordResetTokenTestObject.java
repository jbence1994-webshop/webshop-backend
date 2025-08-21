package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.user.PasswordResetTokenTestConstants.EXPIRED_PASSWORD_RESET_TOKEN_EXPIRATION_DATE;
import static com.github.jbence1994.webshop.user.PasswordResetTokenTestConstants.NOT_EXPIRED_PASSWORD_RESET_TOKEN_EXPIRATION_DATE;
import static com.github.jbence1994.webshop.user.UserTestObject.user;

public final class PasswordResetTokenTestObject {

    public static PasswordResetToken notExpiredPasswordResetToken() {
        return buildPasswordResetToken(NOT_EXPIRED_PASSWORD_RESET_TOKEN_EXPIRATION_DATE);
    }

    public static PasswordResetToken expiredPasswordResetToken() {
        return buildPasswordResetToken(EXPIRED_PASSWORD_RESET_TOKEN_EXPIRATION_DATE);
    }

    private static PasswordResetToken buildPasswordResetToken(LocalDateTime expirationDate) {
        return new PasswordResetToken(1L, "token", user(), expirationDate);
    }
}
