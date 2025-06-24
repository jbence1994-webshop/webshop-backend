package com.github.jbence1994.webshop.checkout;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckoutRequest {

    @NotNull(message = "Cart ID must be provided.")
    private UUID cartId;

    private RewardPointsAction action;
}
