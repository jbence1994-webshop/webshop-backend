package com.github.jbence1994.webshop.checkout;

import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_URL;

public final class CompleteCheckoutSessionTestObject {
    public static CompleteCheckoutSession completeCheckoutSession() {
        return new CompleteCheckoutSession(1L, CHECKOUT_URL);
    }
}
