package com.github.jbence1994.webshop.checkout;

import java.math.BigDecimal;

public interface LoyaltyPointsCalculator {
    int calculateLoyaltyPoints(BigDecimal orderTotal);
}
