package com.github.jbence1994.webshop.user;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class RewardPointsCalculatorImpl implements RewardPointsCalculator {

    @Override
    public int calculateRewardPoints(BigDecimal orderTotalPrice, double multiplier) {
        return orderTotalPrice
                .multiply(BigDecimal.valueOf(multiplier))
                .setScale(0, RoundingMode.DOWN)
                .intValue();
    }
}
