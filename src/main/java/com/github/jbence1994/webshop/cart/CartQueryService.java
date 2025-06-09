package com.github.jbence1994.webshop.cart;

import java.util.UUID;

public interface CartQueryService {
    Cart getCart(UUID id);
}
