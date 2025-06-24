package com.github.jbence1994.webshop.user;

import java.time.LocalDate;

public record ProfileDto(
        String firstName,
        String middleName,
        String lastName,
        LocalDate dateOfBirth,
        String phoneNumber,
        int loyaltyPoints,
        AddressDto address
) {
}
