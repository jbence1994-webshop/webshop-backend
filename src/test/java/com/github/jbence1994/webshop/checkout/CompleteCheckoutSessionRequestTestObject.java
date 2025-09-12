package com.github.jbence1994.webshop.checkout;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;

public final class CompleteCheckoutSessionRequestTestObject {

    public static CompleteCheckoutSessionRequest completeCheckoutSessionRequest() {
        return new CompleteCheckoutSessionRequest(CART_ID);
    }
}
