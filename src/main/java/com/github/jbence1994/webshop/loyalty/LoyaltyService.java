package com.github.jbence1994.webshop.loyalty;

import java.math.BigDecimal;

public interface LoyaltyService {
    int calculateLoyaltyPointsAmount(BigDecimal orderTotal);
}
