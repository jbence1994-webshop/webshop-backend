package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.user.EncryptedUserTestObject.encryptedUser1WithoutAvatar;
import static com.github.jbence1994.webshop.user.RecoveryCodeTestConstants.EXPIRED_RECOVERY_CODE;
import static com.github.jbence1994.webshop.user.RecoveryCodeTestConstants.NOT_EXPIRED_RECOVERY_CODE;
import static com.github.jbence1994.webshop.user.RecoveryCodeTestConstants.RECOVERY_CODE;

public final class RecoveryCodeTestObject {
    public static RecoveryCode notExpiredRecoveryCode() {
        return buildRecoveryCode(NOT_EXPIRED_RECOVERY_CODE);
    }

    public static RecoveryCode expiredRecoveryCode() {
        return buildRecoveryCode(EXPIRED_RECOVERY_CODE);
    }

    private static RecoveryCode buildRecoveryCode(LocalDateTime expirationDate) {
        return new RecoveryCode(1L, RECOVERY_CODE, encryptedUser1WithoutAvatar(), expirationDate);
    }
}
