package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.coupon.DiscountType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartTotalAdjustmentStrategyFactory {

    private static final Map<DiscountType, CartTotalAdjustmentStrategy> CART_TOTAL_ADJUSTMENT_STRATEGIES = Map.of(
            DiscountType.FIXED_AMOUNT, new FixedAmountCartTotalAdjustmentStrategy(),
            DiscountType.PERCENT_OFF, new PercentOffCartTotalAdjustmentStrategy()
    );

    public static CartTotalAdjustmentStrategy getStrategy(DiscountType type) {
        return CART_TOTAL_ADJUSTMENT_STRATEGIES.get(type);
    }
}
