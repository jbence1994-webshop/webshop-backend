package com.github.jbence1994.webshop.cart;

import java.util.ArrayList;
import java.util.List;

import static com.github.jbence1994.webshop.cart.CartItemDtoTestObject.cartItemDto;
import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;

public final class CartDtoTestObject {
    public static CartDto cartDto() {
        return buildCartDto(List.of(cartItemDto()));
    }

    public static CartDto emptyCartDto() {
        return buildCartDto(new ArrayList<>());
    }

    public static CartDto cartDtoWithOneItemAndPercentOffTypeOfAppliedCoupon() {
        return buildCartDto(List.of(cartItemDto()));
    }

    private static CartDto buildCartDto(List<CartItemDto> cartItems) {
        return new CartDto(CART_ID, cartItems, null, null);
    }
}
