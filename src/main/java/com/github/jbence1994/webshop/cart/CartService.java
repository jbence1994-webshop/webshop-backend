package com.github.jbence1994.webshop.cart;

import java.util.UUID;

public interface CartService {
    Cart createCart();

    CartItem addItemToCart(UUID cartId, Long productId);

    CartItem updateCartItem(
            UUID cartId,
            Long productId,
            int quantity
    );

    void deleteCartItem(UUID cartId, Long productId);

    void clearCart(UUID id);

    void deleteCart(UUID id);

    Cart applyCouponToCart(UUID id, String couponCode);

    Cart removeCouponFromCart(UUID id);
}
