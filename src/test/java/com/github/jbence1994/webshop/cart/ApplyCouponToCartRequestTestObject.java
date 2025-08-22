package com.github.jbence1994.webshop.cart;

import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;

public final class ApplyCouponToCartRequestTestObject {
    public static ApplyCouponToCartRequest notSanitizedApplyCouponToCartRequest() {
        return buildApplyCouponToCartRequest(" " + COUPON_1_CODE + " ");
    }

    public static ApplyCouponToCartRequest applyCouponToCartRequest() {
        return buildApplyCouponToCartRequest(COUPON_1_CODE);
    }

    private static ApplyCouponToCartRequest buildApplyCouponToCartRequest(String couponCode) {
        return new ApplyCouponToCartRequest(couponCode);
    }
}
