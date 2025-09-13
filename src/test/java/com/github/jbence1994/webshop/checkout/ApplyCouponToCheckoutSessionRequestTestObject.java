package com.github.jbence1994.webshop.checkout;

import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;

public final class ApplyCouponToCheckoutSessionRequestTestObject {
    public static ApplyCouponToCheckoutSessionRequest applyCouponToCheckoutSessionRequest() {
        return new ApplyCouponToCheckoutSessionRequest(COUPON_1_CODE);
    }

    public static ApplyCouponToCheckoutSessionRequest notSanitizedApplyCouponToCheckoutSessionRequest() {
        return new ApplyCouponToCheckoutSessionRequest(" " + COUPON_1_CODE + " ");
    }
}
