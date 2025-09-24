package com.github.jbence1994.webshop.checkout;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;

public final class CreateCheckoutSessionRequestTestObject {
    public static CreateCheckoutSessionRequest createCheckoutSessionRequest() {
        return new CreateCheckoutSessionRequest(CART_ID);
    }
}
