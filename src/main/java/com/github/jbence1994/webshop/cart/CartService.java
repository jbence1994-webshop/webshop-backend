package com.github.jbence1994.webshop.cart;

import java.util.UUID;

public interface CartService {
    CartItem updateCartItemQuantity(
            UUID cartId,
            Long productId,
            int quantity
    );
}
