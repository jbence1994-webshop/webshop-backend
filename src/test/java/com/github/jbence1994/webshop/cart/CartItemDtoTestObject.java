package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.cart.CartProductDtoTestObject.cartProductDto1;
import static com.github.jbence1994.webshop.cart.CartProductDtoTestObject.cartProductDto2;

public final class CartItemDtoTestObject {
    public static CartItemDto cartItemDto1() {
        return buildCartItemDto(cartProductDto1(), 1, BigDecimal.valueOf(49.99));
    }

    public static CartItemDto cartItemDto2() {
        return buildCartItemDto(cartProductDto2(), 1, BigDecimal.valueOf(89.99));
    }

    public static CartItemDto updatedCartItemDto() {
        return buildCartItemDto(cartProductDto1(), 2, BigDecimal.valueOf(99.98));
    }

    private static CartItemDto buildCartItemDto(CartProductDto cartProductDto, int quantity, BigDecimal totalPrice) {
        return new CartItemDto(cartProductDto, quantity, totalPrice);
    }
}
