package com.github.jbence1994.webshop.cart;

import static com.github.jbence1994.webshop.cart.CartTestObject.cart1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;

public final class CartItemTestObject {
    public static CartItem cartItem() {
        return new CartItem(
                1L,
                cart1(),
                product1(),
                1
        );
    }
}
