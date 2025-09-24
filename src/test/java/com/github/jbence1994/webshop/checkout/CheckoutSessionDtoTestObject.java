package com.github.jbence1994.webshop.checkout;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_SESSION_ID;
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
                BigDecimal.valueOf(49.99),
                BigDecimal.valueOf(49.99),
                appliedCoupon,
                CheckoutStatus.PENDING.name()
        );
    }
}
