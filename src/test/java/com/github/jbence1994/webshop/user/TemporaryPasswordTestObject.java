package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.user.EncryptedUserTestObject.encryptedUser1WithoutAvatar;
import static com.github.jbence1994.webshop.user.TemporaryPasswordTestConstants.EXPIRED_TEMPORARY_PASSWORD;
import static com.github.jbence1994.webshop.user.TemporaryPasswordTestConstants.HASHED_TEMPORARY_PASSWORD;
import static com.github.jbence1994.webshop.user.TemporaryPasswordTestConstants.NOT_EXPIRED_TEMPORARY_PASSWORD;

public final class TemporaryPasswordTestObject {
    public static TemporaryPassword notExpiredTemporaryPassword() {
        return buildTemporaryPassword(NOT_EXPIRED_TEMPORARY_PASSWORD);
    }

    public static TemporaryPassword expiredTemporaryPassword() {
        return buildTemporaryPassword(EXPIRED_TEMPORARY_PASSWORD);
    }

    private static TemporaryPassword buildTemporaryPassword(LocalDateTime expirationDate) {
        return new TemporaryPassword(1L, HASHED_TEMPORARY_PASSWORD, encryptedUser1WithoutAvatar(), expirationDate);
    }
}
