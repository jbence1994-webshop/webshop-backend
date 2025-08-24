package com.github.jbence1994.webshop.cart;

import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;

public final class ApplyCouponToCartRequestTestObject {
    public static ApplyCouponToCartRequest applyCouponToCartRequest() {
        return new ApplyCouponToCartRequest(COUPON_1_CODE);
    }
}
