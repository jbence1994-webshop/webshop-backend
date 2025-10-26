package com.github.jbence1994.webshop.checkout;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateCheckoutSessionRequest(

        @NotNull(message = "Cart ID must be provided.")
        UUID cartId
) {
}
