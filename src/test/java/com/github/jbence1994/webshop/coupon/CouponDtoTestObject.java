package com.github.jbence1994.webshop.coupon;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.coupon.CouponTestConstants.CODE_1;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.NOT_EXPIRED_COUPON_EXPIRATION_DATE;

public final class CouponDtoTestObject {
    public static CouponDto notExpiredCouponDto() {
        return new CouponDto(
                CODE_1,
                BigDecimal.valueOf(5.00),
                NOT_EXPIRED_COUPON_EXPIRATION_DATE
        );
    }
}
