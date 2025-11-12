package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.user.RecoveryCodeTestConstants.RECOVERY_CODE;
import static com.github.jbence1994.webshop.user.RecoveryCodeTestConstants.RESET_TOKEN;
import static com.github.jbence1994.webshop.user.UserTestObject.user1WithoutAvatar;

public final class RecoveryCodeTestObject {
    public static RecoveryCode notExpiredRecoveryCode() {
        return buildRecoveryCode(LocalDateTime.of(9999, 12, 31, 23, 59, 59));
    }

    public static RecoveryCode expiredRecoveryCode() {
        return buildRecoveryCode(LocalDateTime.of(2025, 3, 31, 23, 59, 59));
    }

    private static RecoveryCode buildRecoveryCode(LocalDateTime expirationDate) {
        Byte attempts = 1;

        return new RecoveryCode(1L, user1WithoutAvatar(), RECOVERY_CODE, RESET_TOKEN, LocalDateTime.now(), attempts, expirationDate);
    }
}
