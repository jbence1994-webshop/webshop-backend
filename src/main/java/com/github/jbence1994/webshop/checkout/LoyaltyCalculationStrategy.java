package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.cart.CartItem;

import java.util.List;

public interface LoyaltyCalculationStrategy {
    int calculateLoyaltyPoints(List<CartItem> items);
}
