package com.github.jbence1994.webshop.checkout;

import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_URL;

public final class CheckoutSessionResponseTestObject {

    public static CheckoutSessionResponse checkoutSessionResponse() {
        return new CheckoutSessionResponse(CHECKOUT_URL);
    }
}
