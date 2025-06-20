package com.github.jbence1994.webshop.checkout;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;

public final class CheckoutRequestTestObject {

    public static CheckoutRequest checkoutRequest() {
        return new CheckoutRequest(CART_ID);
    }
}
