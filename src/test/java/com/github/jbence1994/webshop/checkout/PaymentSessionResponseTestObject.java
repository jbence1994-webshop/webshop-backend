package com.github.jbence1994.webshop.checkout;

import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_URL;

public final class PaymentSessionResponseTestObject {
    public static PaymentSessionResponse paymentSessionResponse() {
        return new PaymentSessionResponse(CHECKOUT_URL);
    }
}
