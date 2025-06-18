package com.github.jbence1994.webshop.cart;

import com.github.jbence1994.webshop.coupon.DiscountType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PriceAdjustmentStrategyFactory {
    private final Map<DiscountType, PriceAdjustmentStrategy> priceAdjustmentStrategies = Map.of(
            DiscountType.FIXED_AMOUNT, new FixedAmountPriceAdjustmentStrategy(),
            DiscountType.PERCENT_OFF, new PercentOffPriceAdjustmentStrategy()
    );

    public PriceAdjustmentStrategy getPriceAdjustmentStrategy(DiscountType type) {
        return priceAdjustmentStrategies.get(type);
    }
}
