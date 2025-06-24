package com.github.jbence1994.webshop.user;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class LoyaltyPointsCalculator20 implements LoyaltyPointsCalculator {

    @Override
    public int calculateLoyaltyPoints(BigDecimal orderTotalPrice) {
        var earnedPoints = orderTotalPrice
                .divide(BigDecimal.valueOf(20), RoundingMode.DOWN)
                .setScale(0, RoundingMode.DOWN);

        return earnedPoints.intValue();
    }
}
