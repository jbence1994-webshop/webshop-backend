package com.github.jbence1994.webshop.coupon;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_DESCRIPTION;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.NOT_EXPIRED_COUPON_EXPIRATION_DATE;

public final class CouponDtoTestObject {
    public static CouponDto couponDto1() {
        return new CouponDto(
                COUPON_1_CODE,
                DiscountType.PERCENT_OFF.name(),
                BigDecimal.valueOf(0.10),
                COUPON_1_DESCRIPTION,
                NOT_EXPIRED_COUPON_EXPIRATION_DATE
        );
    }
}
