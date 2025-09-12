package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.github.jbence1994.webshop.cart.CartItemDtoTestObject.cartItemDto1;
import static com.github.jbence1994.webshop.cart.CartItemDtoTestObject.cartItemDto2;
import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;

public final class CartDtoTestObject {
    public static CartDto cartDto() {
        return buildCartDto(List.of(cartItemDto1()), null, BigDecimal.valueOf(49.99));
    }

    public static CartDto emptyCartDto() {
        return buildCartDto(new ArrayList<>(), null, BigDecimal.ZERO);
    }

    public static CartDto cartDtoWithTwoItemsAndPercentOffTypeOfAppliedCoupon() {
        return buildCartDto(
                List.of(cartItemDto1(), cartItemDto2()),
                COUPON_1_CODE,
                BigDecimal.valueOf(125.99)
        );
    }

    private static CartDto buildCartDto(List<CartItemDto> cartItems, String couponCode, BigDecimal totalPrice) {
        return new CartDto(CART_ID, cartItems, couponCode, totalPrice);
    }
}
