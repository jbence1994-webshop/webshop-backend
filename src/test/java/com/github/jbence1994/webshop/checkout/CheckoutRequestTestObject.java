package com.github.jbence1994.webshop.checkout;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;

public final class CheckoutRequestTestObject {

    public static CheckoutRequest checkoutRequestWithInactiveRewardPointsBurning() {
        return buildCheckoutRequest(false);
    }

    public static CheckoutRequest checkoutRequestWithActiveRewardPointsBurning() {
        return buildCheckoutRequest(true);
    }

    private static CheckoutRequest buildCheckoutRequest(boolean rewardPointsBurnActive) {
        return new CheckoutRequest(CART_ID, rewardPointsBurnActive);
    }
}
