package com.github.jbence1994.webshop.checkout;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class LoyaltyPointsCalculatorImpl implements LoyaltyPointsCalculator {
    private final LoyaltyConfig loyaltyConfig;

    @Override
    public int calculateLoyaltyPoints(BigDecimal orderTotal) {
        return orderTotal
                .divide(BigDecimal.valueOf(loyaltyConfig.pointsRate()), 0, RoundingMode.DOWN)
                .intValue();
    }
}
