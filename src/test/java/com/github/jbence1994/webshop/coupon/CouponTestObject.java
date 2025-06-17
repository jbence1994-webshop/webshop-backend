package com.github.jbence1994.webshop.coupon;

import java.math.BigDecimal;
import java.util.Set;

import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_DESCRIPTION;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_2_CODE;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_2_DESCRIPTION;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_3_CODE;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_3_DESCRIPTION;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.EXPIRED_COUPON_EXPIRATION_DATE;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.NOT_EXPIRED_COUPON_EXPIRATION_DATE;
import static com.github.jbence1994.webshop.user.UserTestObject.user;

public final class CouponTestObject {
    public static Coupon coupon1() {
        return new Coupon(
                COUPON_1_CODE,
                DiscountType.PERCENT_OFF,
                BigDecimal.valueOf(0.10),
                COUPON_1_DESCRIPTION,
                NOT_EXPIRED_COUPON_EXPIRATION_DATE,
                Set.of(user())
        );
    }

    public static Coupon coupon2() {
        return new Coupon(
                COUPON_2_CODE,
                DiscountType.FIXED_AMOUNT,
                BigDecimal.valueOf(5.00),
                COUPON_2_DESCRIPTION,
                NOT_EXPIRED_COUPON_EXPIRATION_DATE,
                Set.of(user())
        );
    }

    public static Coupon coupon3() {
        return new Coupon(
                COUPON_3_CODE,
                DiscountType.PERCENT_OFF,
                BigDecimal.valueOf(15.00),
                COUPON_3_DESCRIPTION,
                EXPIRED_COUPON_EXPIRATION_DATE,
                Set.of(user())
        );
    }
}
