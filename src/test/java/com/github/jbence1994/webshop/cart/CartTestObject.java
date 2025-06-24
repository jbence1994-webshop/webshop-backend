package com.github.jbence1994.webshop.cart;

import java.util.ArrayList;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_CREATED_AT;
import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.coupon1;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.coupon2;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.coupon4;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2;

public final class CartTestObject {
    public static Cart cartWithTwoItems() {
        var cart = buildCart();
        cart.addItem(product1());
        cart.addItem(product2());
        return cart;
    }

    public static Cart cartWithOneItem() {
        var cart = buildCart();
        cart.addItem(product2());
        return cart;
    }

    public static Cart updatedCart() {
        var cart = buildCart();
        cart.addItem(product1());
        cart.addItem(product1());
        cart.addItem(product2());
        return cart;
    }

    public static Cart emptyCart() {
        return buildCart();
    }

    public static Cart cartWithTwoItemsAndFixedAmountTypeOfAppliedCoupon() {
        var cart = buildCart();
        cart.addItem(product1());
        cart.addItem(product2());
        cart.setAppliedCoupon(coupon2());
        return cart;
    }

    public static Cart cartWithTwoItemsAndPercentOffTypeOfAppliedCoupon() {
        var cart = buildCart();
        cart.addItem(product1());
        cart.addItem(product2());
        cart.setAppliedCoupon(coupon1());
        return cart;
    }

    public static Cart cartWithTwoItemsAndFreeShippingTypeOfAppliedCoupon() {
        var cart = buildCart();
        cart.addItem(product1());
        cart.addItem(product2());
        cart.setAppliedCoupon(coupon4());
        return cart;
    }

    private static Cart buildCart() {
        return new Cart(
                CART_ID,
                CART_CREATED_AT,
                new ArrayList<>(),
                null
        );
    }
}
