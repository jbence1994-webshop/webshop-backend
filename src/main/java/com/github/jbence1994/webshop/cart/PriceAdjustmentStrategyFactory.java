package com.github.jbence1994.webshop.cart;

import com.github.jbence1994.webshop.coupon.DiscountType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PriceAdjustmentStrategyFactory {

    private static final Map<DiscountType, PriceAdjustmentStrategy> PRICE_ADJUSTMENT_STRATEGIES = Map.of(
            DiscountType.FIXED_AMOUNT, new FixedAmountPriceAdjustmentStrategy(),
            DiscountType.PERCENT_OFF, new PercentOffPriceAdjustmentStrategy()
    );

    public static PriceAdjustmentStrategy getPriceAdjustmentStrategy(DiscountType type) {
        return PRICE_ADJUSTMENT_STRATEGIES.get(type);
    }
}
