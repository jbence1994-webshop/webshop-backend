package com.github.jbence1994.webshop.coupon;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CouponDto(

        @NotNull(message = "Code must be provided.")
        @NotBlank(message = "Code must be not empty.")
        String code,

        @NotNull(message = "Type must be provided.")
        DiscountType type,

        @NotNull(message = "Value must not be null.")
        @Positive(message = "Value must be greater than zero.")
        @DecimalMin(value = "0.00")
        BigDecimal value,

        @NotNull(message = "Description must not be null.")
        @NotEmpty(message = "Description must not empty.")
        String description,

        @NotNull(message = "Expiration date must not be null.")
        LocalDateTime expirationDate
) {
}
