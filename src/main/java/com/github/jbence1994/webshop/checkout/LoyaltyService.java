package com.github.jbence1994.webshop.checkout;

import java.math.BigDecimal;

public interface LoyaltyService {
    int calculateLoyaltyPoints(BigDecimal orderTotal);
}
