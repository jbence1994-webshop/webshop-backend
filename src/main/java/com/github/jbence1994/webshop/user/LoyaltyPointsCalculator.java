package com.github.jbence1994.webshop.user;

import java.math.BigDecimal;

public interface LoyaltyPointsCalculator {
    int calculateLoyaltyPoints(BigDecimal orderTotalPrice);
}
