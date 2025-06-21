package com.github.jbence1994.webshop.user;

import java.math.BigDecimal;

public interface RewardPointsCalculator {
    int calculateRewardPoints(BigDecimal orderTotalPrice, double multiplier);
}
