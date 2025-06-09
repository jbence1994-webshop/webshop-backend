package com.github.jbence1994.webshop.cart;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartQueryServiceImpl implements CartQueryService {
    private final CartRepository cartRepository;

    @Override
    public Cart getCart(UUID id) {
        return cartRepository
                .findById(id)
                .orElseThrow(() -> new CartNotFoundException(id));
    }
}
