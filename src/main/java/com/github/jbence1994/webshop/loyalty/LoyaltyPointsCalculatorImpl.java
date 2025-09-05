package com.github.jbence1994.webshop.loyalty;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class LoyaltyPointsCalculatorImpl implements LoyaltyPointsCalculator {
    private final LoyaltyConfig loyaltyConfig;

    @Override
    public int calculateLoyaltyPoints(BigDecimal orderTotalPrice) {
        return orderTotalPrice
                .divide(BigDecimal.valueOf(loyaltyConfig.pointsRate()), RoundingMode.DOWN)
                .setScale(0, RoundingMode.DOWN)
                .intValue();
    }
}
