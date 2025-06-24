package com.github.jbence1994.webshop.coupon;

import java.time.LocalDateTime;

public interface CouponTestConstants {
    String COUPON_1_CODE = "WELCOME10";
    String COUPON_2_CODE = "SPRING15";
    String COUPON_3_CODE = "FREESHIP";
    String INVALID_COUPON_CODE = "INVALID_COUPON_CODE";
    String COUPON_1_DESCRIPTION = "10% off welcome coupon";
    String COUPON_2_DESCRIPTION = "$15 cashback spring promotion";
    String COUPON_3_DESCRIPTION = "Free shipping on all orders";
    LocalDateTime NOT_EXPIRED_COUPON_EXPIRATION_DATE = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
    LocalDateTime EXPIRED_COUPON_EXPIRATION_DATE = LocalDateTime.of(2025, 3, 31, 23, 59, 59);
}
