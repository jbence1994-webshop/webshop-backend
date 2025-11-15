package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.NOT_EXPIRED_COUPON_EXPIRATION_DATE;

public final class UserCouponDtoTestObject {
    public static UserCouponDto userCouponDto() {
        return new UserCouponDto(COUPON_1_CODE, NOT_EXPIRED_COUPON_EXPIRATION_DATE);
    }
}
