package com.github.jbence1994.webshop.order;

import java.math.BigDecimal;

public final class OrderPricingTestObject {
    public static OrderPricing orderPricingRewardPointsEarn() {
        return OrderPricing.of(BigDecimal.valueOf(49.99), BigDecimal.valueOf(49.99), BigDecimal.ZERO);
    }
}
