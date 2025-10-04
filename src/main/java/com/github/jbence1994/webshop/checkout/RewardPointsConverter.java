package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.user.MembershipTier;

import java.math.BigDecimal;

public interface RewardPointsConverter {
    int toRewardPoints(BigDecimal monetaryAmount, MembershipTier membershipTier);
}
