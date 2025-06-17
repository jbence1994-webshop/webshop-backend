package com.github.jbence1994.webshop.coupon;

import java.time.LocalDateTime;

public interface CouponTestConstants {
    String NOT_EXPIRED_COUPON_CODE = "WELCOME10";
    String EXPIRED_COUPON_CODE = "SPRING15";
    String NOT_EXPIRED_COUPON_DESCRIPTION = "10% off welcome coupon";
    String EXPIRED_COUPON_DESCRIPTION = "15% off spring promotion";
    LocalDateTime NOT_EXPIRED_COUPON_EXPIRATION_DATE = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
    LocalDateTime EXPIRED_COUPON_EXPIRATION_DATE = LocalDateTime.of(2025, 3, 31, 23, 59, 59);
}
