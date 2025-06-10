package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.github.jbence1994.webshop.cart.CartItemDtoTestObject.cartItemDto;
import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;

public final class CartDtoTestObject {
    public static CartDto cartDto() {
        return new CartDto(
                CART_ID,
                List.of(cartItemDto()),
                BigDecimal.valueOf(49.99)
        );
    }

    public static CartDto emptyCartDto() {
        return new CartDto(
                CART_ID,
                new ArrayList<>(),
                BigDecimal.ZERO
        );
    }
}
