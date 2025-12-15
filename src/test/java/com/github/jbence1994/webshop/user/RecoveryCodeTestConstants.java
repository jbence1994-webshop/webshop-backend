package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

public interface RecoveryCodeTestConstants {
    String RECOVERY_CODE = "123456";
    String INVALID_RECOVERY_CODE = "654321";
    LocalDateTime NOT_EXPIRED_RECOVERY_CODE_EXPIRATION_DATE = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
    LocalDateTime EXPIRED_RECOVERY_CODE_EXPIRATION_DATE = LocalDateTime.of(2025, 3, 31, 23, 59, 59);
}
