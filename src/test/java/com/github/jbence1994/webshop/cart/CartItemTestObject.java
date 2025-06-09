package com.github.jbence1994.webshop.cart;

import static com.github.jbence1994.webshop.cart.CartTestObject.cart1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2;

public final class CartItemTestObject {
    public static CartItem cartItem1() {
        return new CartItem(
                1L,
                cart1(),
                product1(),
                1
        );
    }

    public static CartItem cartItem2() {
        return new CartItem(
                2L,
                cart1(),
                product2(),
                2
        );
    }
}
