package com.github.jbence1994.webshop.cart;

import java.util.UUID;

public interface CartService {
    Cart createCart();

    CartItem addProductToCart(UUID cartId, Long productId);

    CartItem updateCartItem(
            UUID cartId,
            Long productId,
            int quantity
    );

    void deleteCartItem(UUID cartId, Long productId);

    void clearCart(UUID cartId);
}
