package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.cart.CartProductDtoTestObject.cartProductDto;

public final class CartItemDtoTestObject {
    public static CartItemDto cartItemDto() {
        return new CartItemDto(
                cartProductDto(),
                1,
                BigDecimal.valueOf(49.99)
        );
    }
}
