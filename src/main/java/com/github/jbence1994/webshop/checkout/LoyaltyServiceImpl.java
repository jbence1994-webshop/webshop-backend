package com.github.jbence1994.webshop.checkout;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class LoyaltyServiceImpl implements LoyaltyService {
    private final LoyaltyPointsConfig loyaltyPointsConfig;

    @Override
    public int calculateLoyaltyPoints(BigDecimal orderTotal) {
        return orderTotal
                .divide(BigDecimal.valueOf(loyaltyPointsConfig.rate()), 0, RoundingMode.DOWN)
                .intValue();
    }
}
