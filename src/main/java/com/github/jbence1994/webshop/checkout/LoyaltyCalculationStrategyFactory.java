package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.user.MembershipTier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoyaltyCalculationStrategyFactory {

    private static final Map<MembershipTier, LoyaltyCalculationStrategy> LOYALTY_CALCULATION_STRATEGIES = Map.of(
            MembershipTier.BRONZE, new BronzeCalculationStrategy(),
            MembershipTier.SILVER, new SilverCalculationStrategy(),
            MembershipTier.GOLD, new GoldCalculationStrategy()
    );

    public static LoyaltyCalculationStrategy getLoyaltyCalculationStrategy(MembershipTier tier) {
        return LOYALTY_CALCULATION_STRATEGIES.get(tier);
    }
}
