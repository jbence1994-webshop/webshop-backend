package com.github.jbence1994.webshop.checkout;

import java.math.BigDecimal;

public interface RewardPointsCalculator {
    int calculateRewardPoints(BigDecimal cartTotal, double multiplier);
}
