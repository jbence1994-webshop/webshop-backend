package com.github.jbence1994.webshop.cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartQueryServiceImpl implements CartQueryService {
    private final CartRepository cartRepository;

    @Override
    public Cart getCart(UUID id) {
        return cartRepository
                .findById(id)
                .orElseThrow(() -> new CartNotFoundException(id));
    }

    @Override
    public CartItem getCartItem(UUID cartId, Long productId) {
        var cart = getCart(cartId);

        return cart.getItem(productId)
                .orElseThrow(() -> new CartItemNotFoundException(productId));
    }
}
