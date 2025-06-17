package com.github.jbence1994.webshop.coupon;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.coupon.CouponTestConstants.NOT_EXPIRED_COUPON_CODE;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.NOT_EXPIRED_COUPON_DESCRIPTION;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.NOT_EXPIRED_COUPON_EXPIRATION_DATE;

public final class CouponDtoTestObject {
    public static CouponDto notExpiredCouponDto() {
        return new CouponDto(
                NOT_EXPIRED_COUPON_CODE,
                DiscountType.PERCENT_OFF.name(),
                BigDecimal.valueOf(10.00),
                NOT_EXPIRED_COUPON_DESCRIPTION,
                NOT_EXPIRED_COUPON_EXPIRATION_DATE
        );
    }
}
