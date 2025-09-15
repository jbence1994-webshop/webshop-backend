package com.github.jbence1994.webshop.checkout;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CheckoutTestConstants {
    UUID CHECKOUT_SESSION_ID = UUID.fromString("401c3a9e-c1ae-4a39-956b-9af3ed28a4e2");
    LocalDateTime CREATED_AT = LocalDateTime.of(2025, 9, 13, 11, 11, 11);
    String CHECKOUT_URL = "CHECKOUT_URL";
}
