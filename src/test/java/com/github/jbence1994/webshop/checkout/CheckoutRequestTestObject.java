package com.github.jbence1994.webshop.checkout;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;

public final class CheckoutRequestTestObject {

    public static CheckoutRequest earnRewardPointsCheckoutRequest() {
        return buildCheckoutRequest(RewardPointsAction.EARN);
    }

    public static CheckoutRequest burnRewardPointsCheckoutRequest() {
        return buildCheckoutRequest(RewardPointsAction.BURN);
    }

    private static CheckoutRequest buildCheckoutRequest(RewardPointsAction action) {
        return new CheckoutRequest(CART_ID, action);
    }
}
