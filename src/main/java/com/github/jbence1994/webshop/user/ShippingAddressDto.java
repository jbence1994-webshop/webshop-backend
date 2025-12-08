package com.github.jbence1994.webshop.user;

public record ShippingAddressDto(
        String addressLine,
        String municipality,
        String province,
        String postalCode,
        String country
) {
}
