package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.user.MembershipTier;

import java.math.BigDecimal;

public interface LoyaltyConversionService {
    int calculateLoyaltyPoints(BigDecimal orderTotal);

    int calculateRewardPoints(BigDecimal monetaryAmount, MembershipTier membershipTier);
}
