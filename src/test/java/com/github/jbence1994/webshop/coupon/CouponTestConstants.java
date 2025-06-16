package com.github.jbence1994.webshop.coupon;

import java.time.LocalDateTime;

public interface CouponTestConstants {
    String CODE_1 = "5%OFF";
    String CODE_2 = "10%OFF";
    LocalDateTime NOT_EXPIRED_COUPON_EXPIRATION_DATE = LocalDateTime.of(2099, 6, 1, 23, 59, 59);
    LocalDateTime EXPIRED_COUPON_EXPIRATION_DATE = LocalDateTime.of(2025, 6, 12, 0, 0, 0);
}
