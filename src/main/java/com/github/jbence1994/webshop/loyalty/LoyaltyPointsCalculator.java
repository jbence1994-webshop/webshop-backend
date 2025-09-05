package com.github.jbence1994.webshop.loyalty;

import java.math.BigDecimal;

public interface LoyaltyPointsCalculator {
    int calculateLoyaltyPoints(BigDecimal orderTotalPrice);
}
