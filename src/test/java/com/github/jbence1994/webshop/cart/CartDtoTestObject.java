package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.github.jbence1994.webshop.cart.CartItemDtoTestObject.cartItemDto;
import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;

public final class CartDtoTestObject {
    public static CartDto cartDto() {
        return buildCartDto(List.of(cartItemDto()), BigDecimal.valueOf(49.99));
    }

    public static CartDto emptyCartDto() {
        return buildCartDto(new ArrayList<>(), BigDecimal.ZERO);
    }

    public static CartDto cartDtoWithOneItemAndAppliedCoupon() {
        return buildCartDto(List.of(cartItemDto()), BigDecimal.valueOf(49.99));
    }

    private static CartDto buildCartDto(
            List<CartItemDto> cartItems,
            BigDecimal totalPrice
    ) {
        return new CartDto(CART_ID, cartItems, null, totalPrice);
    }
}
