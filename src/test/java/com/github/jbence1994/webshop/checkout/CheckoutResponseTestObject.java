package com.github.jbence1994.webshop.checkout;

import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_URL;

public final class CheckoutResponseTestObject {

    public static CheckoutResponse checkoutResponse() {
        return new CheckoutResponse(1L, CHECKOUT_URL);
    }
}
