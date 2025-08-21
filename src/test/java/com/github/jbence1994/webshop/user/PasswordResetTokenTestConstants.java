package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

public interface PasswordResetTokenTestConstants {
    LocalDateTime NOT_EXPIRED_PASSWORD_RESET_TOKEN_EXPIRATION_DATE = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
    LocalDateTime EXPIRED_PASSWORD_RESET_TOKEN_EXPIRATION_DATE = LocalDateTime.of(2025, 3, 31, 23, 59, 59);
}
