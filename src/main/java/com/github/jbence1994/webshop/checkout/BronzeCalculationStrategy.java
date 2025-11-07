package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.cart.CartItem;
import com.github.jbence1994.webshop.user.MembershipTier;

import java.util.List;

public class BronzeCalculationStrategy implements LoyaltyCalculationStrategy {

    @Override
    public int calculateLoyaltyPoints(List<CartItem> items) {
        var quantityOfItems = items.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();

        return quantityOfItems * MembershipTier.BRONZE.getMultiplier();
    }
}
