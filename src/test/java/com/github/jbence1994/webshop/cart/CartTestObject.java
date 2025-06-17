package com.github.jbence1994.webshop.cart;

import java.util.ArrayList;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_CREATED_AT;
import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2;

public final class CartTestObject {
    public static Cart cartWithTwoItems() {
        var cart = new Cart(
                CART_ID,
                CART_CREATED_AT,
                new ArrayList<>(),
                null
        );
        cart.addItem(product1());
        cart.addItem(product2());
        return cart;
    }

    public static Cart cartWithOneItem() {
        var cart = new Cart(
                CART_ID,
                CART_CREATED_AT,
                new ArrayList<>(),
                null
        );
        cart.addItem(product2());
        return cart;
    }

    public static Cart updatedCart() {
        var cart = new Cart(
                CART_ID,
                CART_CREATED_AT,
                new ArrayList<>(),
                null
        );
        cart.addItem(product1());
        cart.addItem(product1());
        cart.addItem(product2());
        return cart;
    }

    public static Cart emptyCart() {
        return new Cart(
                CART_ID,
                CART_CREATED_AT,
                new ArrayList<>(),
                null
        );
    }
}
