package com.github.jbence1994.webshop.coupon;

import java.math.BigDecimal;
import java.util.Set;

import static com.github.jbence1994.webshop.coupon.CouponTestConstants.CODE_1;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.CODE_2;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.EXPIRED_COUPON_EXPIRATION_DATE;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.NOT_EXPIRED_COUPON_EXPIRATION_DATE;
import static com.github.jbence1994.webshop.user.UserTestObject.user;

public final class CouponTestObject {
    public static Coupon notExpiredCoupon() {
        return new Coupon(
                CODE_1,
                BigDecimal.valueOf(5.00),
                NOT_EXPIRED_COUPON_EXPIRATION_DATE,
                Set.of(user())
        );
    }

    public static Coupon expiredCoupon() {
        return new Coupon(
                CODE_2,
                BigDecimal.valueOf(10.00),
                EXPIRED_COUPON_EXPIRATION_DATE,
                Set.of(user())
        );
    }
}
