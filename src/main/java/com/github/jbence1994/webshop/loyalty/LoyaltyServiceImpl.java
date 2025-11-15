package com.github.jbence1994.webshop.loyalty;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class LoyaltyServiceImpl implements LoyaltyService {
    private final LoyaltyConversionConfig loyaltyConversionConfig;

    @Override
    public int calculateLoyaltyPointsAmount(BigDecimal orderTotal) {
        return orderTotal
                .divide(loyaltyConversionConfig.rate(), 0, RoundingMode.DOWN)
                .intValue();
    }
}
