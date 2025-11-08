package com.github.jbence1994.webshop.loyalty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum MembershipTier {
    BRONZE(0, 4_999),
    SILVER(5_000, 9_999),
    GOLD(10_000, Integer.MAX_VALUE);

    private final int minPoints;
    private final int maxPoints;

    public static MembershipTier fromPoints(int value) {
        return Stream.of(values())
                .filter(tier -> value >= tier.minPoints && value <= tier.maxPoints)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Invalid value: %d.", value)));
    }
}
