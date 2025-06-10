package com.github.jbence1994.webshop.cart;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartQueryService cartQueryService;
    private final CartRepository cartRepository;

    @Override
    public CartItem updateCartItemQuantity(
            UUID cartId,
            Long productId,
            int quantity
    ) {
        var cart = cartQueryService.getCart(cartId);
        var cartItem = cartQueryService.getCartItem(cartId, productId);

        cartItem.setQuantity(quantity);
        cartRepository.save(cart);

        return cartItem;
    }
}
