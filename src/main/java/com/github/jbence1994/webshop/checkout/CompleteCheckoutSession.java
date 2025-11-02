package com.github.jbence1994.webshop.checkout;

public record CompleteCheckoutSession(Long orderId, String checkoutUrl) {
}
