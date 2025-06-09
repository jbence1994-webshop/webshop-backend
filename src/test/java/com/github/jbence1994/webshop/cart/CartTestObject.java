package com.github.jbence1994.webshop.cart;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.UUID;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2;

public final class CartTestObject {
    public static Cart cart1() {
        var cart = new Cart(
                UUID.randomUUID(),
                LocalDate.now(),
                new HashSet<>()
        );
        cart.addItem(product1());
        return cart;
    }

    public static Cart cart2() {
        var cart = new Cart(
                UUID.randomUUID(),
                LocalDate.now(),
                new HashSet<>()
        );
        cart.addItem(product1());
        cart.addItem(product2());
        return cart;
    }

    public static Cart emptyCart() {
        return new Cart(
                UUID.randomUUID(),
                LocalDate.now(),
                new HashSet<>()
        );
    }
}
