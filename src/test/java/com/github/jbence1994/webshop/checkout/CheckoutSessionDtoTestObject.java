package com.github.jbence1994.webshop.checkout;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_SESSION_ID;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CREATED_AT;

public final class CheckoutSessionDtoTestObject {
    public static CheckoutSessionDto checkoutSessionDto() {
        return new CheckoutSessionDto(
                CHECKOUT_SESSION_ID,
                CART_ID,
                null,
                CheckoutStatus.PENDING.name(),
                CREATED_AT
        );
    }
}
