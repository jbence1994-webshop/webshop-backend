package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.cart.Cart;
import com.github.jbence1994.webshop.coupon.Coupon;

import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithOneItem;
import static com.github.jbence1994.webshop.cart.CartTestObject.emptyCart;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_SESSION_ID;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CREATED_AT;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.percentOffNotExpiredCoupon;

public final class CheckoutSessionTestObject {
    public static CheckoutSession checkoutSession() {
        return buildCheckoutSession(cartWithOneItem(), null, CheckoutStatus.PENDING);
    }

    public static CheckoutSession checkoutSessionWithPercentOffTypeOfAppliedCoupon() {
        return buildCheckoutSession(cartWithOneItem(), percentOffNotExpiredCoupon(), CheckoutStatus.PENDING);
    }

    public static CheckoutSession checkoutSessionWithEmptyCart() {
        return buildCheckoutSession(emptyCart(), null, CheckoutStatus.PENDING);
    }

    public static CheckoutSession completedCheckoutSession() {
        return buildCheckoutSession(cartWithOneItem(), null, CheckoutStatus.COMPLETED);
    }

    private static CheckoutSession buildCheckoutSession(Cart cart, Coupon appliedCoupon, CheckoutStatus status) {
        return new CheckoutSession(
                CHECKOUT_SESSION_ID,
                cart,
                appliedCoupon,
                status,
                CREATED_AT
        );
    }
}
