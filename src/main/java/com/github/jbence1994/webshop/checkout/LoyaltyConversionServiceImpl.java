package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.user.MembershipTier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class LoyaltyConversionServiceImpl implements LoyaltyConversionService {
    private final LoyaltyPointsConfig loyaltyPointsConfig;

    @Override
    public int calculateLoyaltyPoints(BigDecimal orderTotal) {
        return orderTotal
                .divide(BigDecimal.valueOf(loyaltyPointsConfig.rate()), 0, RoundingMode.DOWN)
                .intValue();
    }

    @Override
    public int calculateRewardPoints(BigDecimal monetaryAmount, MembershipTier membershipTier) {
        var multiplier = MembershipTier.valueOf(membershipTier.name()).getRewardPointsMultiplier();

        return monetaryAmount
                .multiply(BigDecimal.valueOf(multiplier))
                .intValue();
    }
}
