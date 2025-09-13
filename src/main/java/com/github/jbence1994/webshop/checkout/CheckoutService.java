package com.github.jbence1994.webshop.checkout;

import java.util.UUID;

public interface CheckoutService {
    CheckoutSession createCheckoutSession(UUID cartId);

    CompleteCheckoutSessionResponse completeCheckoutSession(UUID checkoutSessionId);
}
