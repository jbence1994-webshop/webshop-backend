package com.github.jbence1994.webshop.checkout;

import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_SESSION_ID;

public final class CompleteCheckoutSessionRequestTestObject {

    public static CompleteCheckoutSessionRequest completeCheckoutSessionRequest() {
        return new CompleteCheckoutSessionRequest(CHECKOUT_SESSION_ID);
    }
}
