package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.coupon.DiscountType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckoutPriceAdjustmentStrategyFactory {

    private static final Map<DiscountType, CheckoutPriceAdjustmentStrategy> CHECKOUT_PRICE_ADJUSTMENT_STRATEGIES = Map.of(
            DiscountType.FIXED_AMOUNT, new FixedAmountCheckoutPriceAdjustmentStrategy(),
            DiscountType.PERCENT_OFF, new PercentOffCheckoutPriceAdjustmentStrategy()
    );

    public static CheckoutPriceAdjustmentStrategy getCheckoutPriceAdjustmentStrategy(DiscountType type) {
        return CHECKOUT_PRICE_ADJUSTMENT_STRATEGIES.get(type);
    }
}
