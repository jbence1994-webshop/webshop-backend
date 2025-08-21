package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

public interface TemporaryPasswordTestConstants {
    String TEMPORARY_PASSWORD = "wrF+tTdn0w8DfGL1iU3KMp1mPwLEekEqUbmUARr/r5o";
    LocalDateTime NOT_EXPIRED_TEMPORARY_PASSWORD = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
    LocalDateTime EXPIRED_TEMPORARY_PASSWORD = LocalDateTime.of(2025, 3, 31, 23, 59, 59);
}
