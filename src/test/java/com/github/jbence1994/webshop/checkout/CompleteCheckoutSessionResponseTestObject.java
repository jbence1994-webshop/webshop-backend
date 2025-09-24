package com.github.jbence1994.webshop.checkout;

import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_URL;

public final class CompleteCheckoutSessionResponseTestObject {
    public static CompleteCheckoutSessionResponse completeCheckoutSessionResponse() {
        return new CompleteCheckoutSessionResponse(1L, CHECKOUT_URL);
    }
}
