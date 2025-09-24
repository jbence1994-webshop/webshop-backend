package com.github.jbence1994.webshop.checkout;

import java.util.UUID;

public interface CheckoutQueryService {
    CheckoutSession getCheckoutSession(UUID id);
}
