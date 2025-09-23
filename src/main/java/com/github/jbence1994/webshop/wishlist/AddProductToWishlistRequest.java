package com.github.jbence1994.webshop.wishlist;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddProductToWishlistRequest {

    @NotNull(message = "Product ID must be provided.")
    private Long productId;
}
