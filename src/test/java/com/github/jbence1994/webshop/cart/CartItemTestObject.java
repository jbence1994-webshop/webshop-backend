package com.github.jbence1994.webshop.cart;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;

public final class CartItemTestObject {
    public static CartItem cartItem() {
        return buildCartItem(1);
    }

    public static CartItem updatedCartItem() {
        return buildCartItem(2);
    }

    private static CartItem buildCartItem(Integer quantity) {
        return new CartItem(1L, null, product1(), quantity);
    }
}
