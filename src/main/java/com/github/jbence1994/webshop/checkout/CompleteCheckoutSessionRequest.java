package com.github.jbence1994.webshop.checkout;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CompleteCheckoutSessionRequest(

        @NotNull(message = "Checkout Session ID must be provided.")
        UUID checkoutSessionId
) {
}
