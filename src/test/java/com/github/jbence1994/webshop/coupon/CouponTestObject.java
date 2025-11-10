package com.github.jbence1994.webshop.coupon;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_DESCRIPTION;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_2_CODE;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_2_DESCRIPTION;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.EXPIRED_COUPON_EXPIRATION_DATE;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.NOT_EXPIRED_COUPON_EXPIRATION_DATE;
import static com.github.jbence1994.webshop.user.ProfileTestObject.profileWithoutAvatar;

public final class CouponTestObject {
    public static Coupon percentOffNotExpiredCoupon() {
        return buildCoupon(
                COUPON_1_CODE,
                DiscountType.PERCENT_OFF,
                BigDecimal.valueOf(0.10),
                COUPON_1_DESCRIPTION,
                NOT_EXPIRED_COUPON_EXPIRATION_DATE
        );
    }

    public static Coupon fixedAmountExpiredCoupon() {
        return buildCoupon(
                COUPON_2_CODE,
                DiscountType.FIXED_AMOUNT,
                BigDecimal.valueOf(15.00),
                COUPON_2_DESCRIPTION,
                EXPIRED_COUPON_EXPIRATION_DATE
        );
    }

    private static Coupon buildCoupon(
            String couponCode,
            DiscountType type,
            BigDecimal value,
            String description,
            LocalDateTime expirationDate
    ) {
        return new Coupon(
                couponCode,
                type,
                value,
                description,
                expirationDate,
                List.of(profileWithoutAvatar())
        );
    }
}
