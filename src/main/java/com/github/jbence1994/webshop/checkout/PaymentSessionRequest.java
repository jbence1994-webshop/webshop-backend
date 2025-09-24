package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.order.Order;

public record PaymentSessionRequest(CheckoutSession checkoutSession, Order order) {
}
