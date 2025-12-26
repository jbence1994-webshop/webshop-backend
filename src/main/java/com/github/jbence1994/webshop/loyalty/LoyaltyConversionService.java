package com.github.jbence1994.webshop.loyalty;

import java.math.BigDecimal;

public interface LoyaltyConversionService {
    int toLoyaltyPoints(BigDecimal monetaryAmount);

    BigDecimal toMonetaryAmount(int loyaltyPoints);
}
