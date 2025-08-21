package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

public interface TemporaryPasswordTestConstants {
    String TEMPORARY_PASSWORD = "0x6+I0kUcYLxwfyCurlIzpzupg2ot+DN5bOUg6Yns/U";
    LocalDateTime NOT_EXPIRED_TEMPORARY_PASSWORD = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
    LocalDateTime EXPIRED_TEMPORARY_PASSWORD = LocalDateTime.of(2025, 3, 31, 23, 59, 59);
}
