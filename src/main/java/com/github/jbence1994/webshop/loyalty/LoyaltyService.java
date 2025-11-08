package com.github.jbence1994.webshop.loyalty;

import java.math.BigDecimal;

public interface LoyaltyService {
    int calculateLoyaltyPoints(BigDecimal cartTotal);
}
