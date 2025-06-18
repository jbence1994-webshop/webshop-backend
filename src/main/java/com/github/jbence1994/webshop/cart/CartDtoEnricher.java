package com.github.jbence1994.webshop.cart;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartDtoEnricher {
    private final PriceAdjustmentStrategyFactory priceAdjustmentStrategyFactory;

    public CartDto enrich(CartDto cartDto, Cart cart) {
        var total = cart.calculateTotalPrice(priceAdjustmentStrategyFactory);
        cartDto.setTotalPrice(total);
        return cartDto;
    }
}
