package com.github.jbence1994.webshop.checkout;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_SESSION_ID;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CREATED_AT;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;

public final class CheckoutSessionDtoTestObject {
    public static CheckoutSessionDto checkoutSessionDto() {
        return buildCheckoutSessionDto(null);
    }

    public static CheckoutSessionDto checkoutSessionDtoWithPercentOffTypeOfAppliedCoupon() {
        return buildCheckoutSessionDto(COUPON_1_CODE);
    }

    private static CheckoutSessionDto buildCheckoutSessionDto(String appliedCoupon) {
        return new CheckoutSessionDto(
                CHECKOUT_SESSION_ID,
                CART_ID,
                appliedCoupon,
                CheckoutStatus.PENDING.name(),
                CREATED_AT
        );
    }
}
