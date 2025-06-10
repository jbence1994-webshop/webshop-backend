package com.github.jbence1994.webshop.cart;

import static com.github.jbence1994.webshop.cart.CartTestObject.cart;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;

public final class CartItemTestObject {
    public static CartItem cartItem() {
        return new CartItem(
                1L,
                cart(),
                product1(),
                1
        );
    }

    public static CartItem updatedCartItem() {
        return new CartItem(
                1L,
                cart(),
                product1(),
                2
        );
    }
}
