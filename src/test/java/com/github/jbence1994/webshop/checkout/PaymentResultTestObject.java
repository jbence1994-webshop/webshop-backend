package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.order.OrderStatus;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_SESSION_ID;

public final class PaymentResultTestObject {
    public static PaymentResult chargeSucceeded() {
        return new PaymentResult(
                "charge.succeeded",
                CART_ID,
                1L,
                CHECKOUT_SESSION_ID,
                OrderStatus.CREATED,
                CheckoutStatus.PENDING
        );
    }

    public static PaymentResult paymentIntentSucceeded() {
        return new PaymentResult(
                "payment_intent.succeeded",
                CART_ID,
                1L,
                CHECKOUT_SESSION_ID,
                OrderStatus.CONFIRMED,
                CheckoutStatus.COMPLETED
        );
    }
}
