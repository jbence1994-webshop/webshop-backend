package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.github.jbence1994.webshop.cart.CartItemDtoTestObject.cartItemDto;
import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;

public final class EnrichedCartDtoTestObject {
    public static CartDto enrichedCartDto() {
        return buildEnrichedCartDto(List.of(cartItemDto()), BigDecimal.valueOf(49.99));
    }

    public static CartDto enrichedEmptyCartDto() {
        return buildEnrichedCartDto(new ArrayList<>(), BigDecimal.ZERO);
    }

    public static CartDto enrichedCartDtoWithOneItemAndPercentOffTypeOfAppliedCoupon() {
        return buildEnrichedCartDto(List.of(cartItemDto()), BigDecimal.valueOf(49.99));
    }

    private static CartDto buildEnrichedCartDto(
            List<CartItemDto> cartItems,
            BigDecimal totalPrice
    ) {
        return new CartDto(CART_ID, cartItems, null, totalPrice);
    }
}
