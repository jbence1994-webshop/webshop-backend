package com.github.jbence1994.webshop.checkout;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CompleteCheckoutSessionRequest(

        @NotNull(message = "Checkout Session ID must be provided.")
        UUID checkoutSessionId,

        @NotNull(message = "Reward points action must be provided.")
        RewardPointsAction action
) {
}
