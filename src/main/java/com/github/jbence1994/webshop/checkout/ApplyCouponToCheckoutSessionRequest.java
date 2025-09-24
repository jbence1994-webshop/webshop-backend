package com.github.jbence1994.webshop.checkout;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApplyCouponToCheckoutSessionRequest {

    @NotNull(message = "Coupon code must be provided.")
    @NotBlank(message = "Coupon code must be not empty.")
    private String couponCode;
}
