package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.product.WishlistProductDto;

import java.time.LocalDate;
import java.util.List;

public record ProfileDto(
        String firstName,
        String middleName,
        String lastName,
        LocalDate dateOfBirth,
        String phoneNumber,
        AddressDto address,
        List<WishlistProductDto> favoriteProducts
) {
}
