package com.github.jbence1994.webshop.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum MembershipTier {
    BRONZE(0, 4_999, 1.5),
    SILVER(5_000, 9_999, 2),
    GOLD(10_000, 19_999, 2.5),
    PLATINUM(20_000, Integer.MAX_VALUE, 5);

    private final int minLoyaltyPoints;
    private final int maxLoyaltyPoints;
    private final double rewardPointsMultiplier;

    public static MembershipTier fromPoints(int value) {
        return Stream.of(values())
                .filter(tier -> value >= tier.minLoyaltyPoints && value <= tier.maxLoyaltyPoints)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Invalid value: %d.", value)));
    }
}
