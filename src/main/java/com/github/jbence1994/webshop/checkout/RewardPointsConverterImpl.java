package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.user.MembershipTier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class RewardPointsConverterImpl implements RewardPointsConverter {

    @Override
    public int toRewardPoints(BigDecimal monetaryAmount, MembershipTier membershipTier) {
        var multiplier = MembershipTier.valueOf(membershipTier.name()).getRewardPointsMultiplier();

        return monetaryAmount
                .multiply(BigDecimal.valueOf(multiplier))
                .intValue();
    }
}
