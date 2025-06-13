package com.github.jbence1994.webshop.coupon;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.coupon.CouponTestConstants.CODE_1;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.CODE_2;

public final class CouponTestObject {
    public static Coupon notExpiredCoupon() {
        return new Coupon(
                CODE_1,
                BigDecimal.valueOf(5.00),
                LocalDateTime.of(2099, 6, 1, 23, 59, 59)
        );
    }

    public static Coupon expiredCoupon() {
        return new Coupon(
                CODE_2,
                BigDecimal.valueOf(10.00),
                LocalDateTime.of(2025, 6, 12, 0, 0, 0)
        );
    }
}
