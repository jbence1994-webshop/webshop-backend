package com.github.jbence1994.webshop.checkout;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class RewardPointsCalculatorImpl implements RewardPointsCalculator {

    @Override
    public int calculateRewardPoints(BigDecimal cartTotal, double multiplier) {
        return cartTotal
                .multiply(BigDecimal.valueOf(multiplier))
                .intValue();
    }
}
