package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

public interface TemporaryPasswordTestConstants {
    String TEMPORARY_PASSWORD = "etNJ0wyy4+YREcvBuiInwflk3ZK59uBAGBvFQGyMCNY";
    String HASHED_TEMPORARY_PASSWORD = "$2a$10$GzdOGceRdrNv5UH5zFYGKeBgRHbR46sFa/D4G2kgjqz4nzP6WInn2";
    LocalDateTime NOT_EXPIRED_TEMPORARY_PASSWORD = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
    LocalDateTime EXPIRED_TEMPORARY_PASSWORD = LocalDateTime.of(2025, 3, 31, 23, 59, 59);
}
