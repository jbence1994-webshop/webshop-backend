package com.github.jbence1994.webshop.loyalty;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class LoyaltyConversionServiceImpl implements LoyaltyConversionService {

    @Override
    public int toLoyaltyPoints(BigDecimal monetaryAmount) {
        return 0;
    }

    @Override
    public BigDecimal toMonetaryAmount(int loyaltyPoints) {
        return null;
    }
}
