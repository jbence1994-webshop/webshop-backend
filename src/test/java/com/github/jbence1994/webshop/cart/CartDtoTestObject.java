package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;

public final class CartDtoTestObject {
    public static CartDto emptyCartDto() {
        return new CartDto(
                CART_ID,
                new ArrayList<>(),
                BigDecimal.ZERO
        );
    }
}
