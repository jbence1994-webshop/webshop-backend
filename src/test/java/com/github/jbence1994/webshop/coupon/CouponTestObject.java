package com.github.jbence1994.webshop.coupon;

import java.math.BigDecimal;
import java.util.Set;

import static com.github.jbence1994.webshop.coupon.CouponTestConstants.EXPIRED_COUPON_CODE;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.EXPIRED_COUPON_DESCRIPTION;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.EXPIRED_COUPON_EXPIRATION_DATE;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.NOT_EXPIRED_COUPON_CODE;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.NOT_EXPIRED_COUPON_DESCRIPTION;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.NOT_EXPIRED_COUPON_EXPIRATION_DATE;
import static com.github.jbence1994.webshop.user.UserTestObject.user;

public final class CouponTestObject {
    public static Coupon notExpiredCoupon() {
        return new Coupon(
                NOT_EXPIRED_COUPON_CODE,
                DiscountType.PERCENT_OFF,
                BigDecimal.valueOf(10.00),
                NOT_EXPIRED_COUPON_DESCRIPTION,
                NOT_EXPIRED_COUPON_EXPIRATION_DATE,
                Set.of(user())
        );
    }

    public static Coupon expiredCoupon() {
        return new Coupon(
                EXPIRED_COUPON_CODE,
                DiscountType.PERCENT_OFF,
                BigDecimal.valueOf(15.00),
                EXPIRED_COUPON_DESCRIPTION,
                EXPIRED_COUPON_EXPIRATION_DATE,
                Set.of(user())
        );
    }
}
