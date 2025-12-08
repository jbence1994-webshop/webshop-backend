package com.github.jbence1994.webshop.user;

import java.time.LocalDate;

public record UserDto(
        Long id,
        String email,
        String firstName,
        String middleName,
        String lastName,
        LocalDate dateOfBirth,
        String phoneNumber,
        BillingAddressDto billingAddress,
        ShippingAddressDto shippingAddress
) {
}
