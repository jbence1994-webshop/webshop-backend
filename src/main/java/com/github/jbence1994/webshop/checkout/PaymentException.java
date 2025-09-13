package com.github.jbence1994.webshop.checkout;

public class PaymentException extends RuntimeException {
    public PaymentException(String message) {
        super(message);
    }
}
