package com.github.jbence1994.webshop.checkout;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class CheckoutRequest {

    @NotNull(message = "Cart ID must be provided.")
    private UUID cartId;

    private RewardPointsAction action;
}
