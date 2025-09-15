package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.cart.Cart;
import com.github.jbence1994.webshop.coupon.Coupon;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithOneItem;
import static com.github.jbence1994.webshop.cart.CartTestObject.emptyCart;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_SESSION_ID;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CREATED_AT;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.percentOffNotExpiredCoupon;

public final class CheckoutSessionTestObject {
    public static CheckoutSession checkoutSession1() {
        return buildCheckoutSession(
                cartWithOneItem(),
                BigDecimal.valueOf(49.99),
                BigDecimal.valueOf(49.99),
                BigDecimal.ZERO,
                null,
                CheckoutStatus.PENDING
        );
    }

    public static CheckoutSession checkoutSessionWithPercentOffTypeOfAppliedCoupon() {
        return buildCheckoutSession(
                cartWithOneItem(),
                BigDecimal.valueOf(49.99),
                BigDecimal.valueOf(5.00),
                BigDecimal.valueOf(44.99),
                percentOffNotExpiredCoupon(),
                CheckoutStatus.PENDING
        );
    }

    public static CheckoutSession checkoutSessionWithEmptyCart() {
        return buildCheckoutSession(
                emptyCart(),
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                null,
                CheckoutStatus.PENDING
        );
    }

    public static CheckoutSession completedCheckoutSession() {
        return buildCheckoutSession(
                cartWithOneItem(),
                BigDecimal.valueOf(49.99),
                BigDecimal.valueOf(49.99),
                BigDecimal.ZERO,
                null,
                CheckoutStatus.COMPLETED
        );
    }

    private static CheckoutSession buildCheckoutSession(
            Cart cart,
            BigDecimal originalCartTotal,
            BigDecimal cartTotal,
            BigDecimal discountAmount,
            Coupon appliedCoupon,
            CheckoutStatus status
    ) {
        return new CheckoutSession(
                CHECKOUT_SESSION_ID,
                cart,
                originalCartTotal,
                cartTotal,
                discountAmount,
                appliedCoupon,
                status,
                CREATED_AT
        );
    }
}
