package com.github.jbence1994.webshop.user;

public record BillingAddressDto(
        String addressLine,
        String municipality,
        String province,
        String postalCode,
        String country
) {
}
