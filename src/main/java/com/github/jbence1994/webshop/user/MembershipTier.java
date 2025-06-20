package com.github.jbence1994.webshop.user;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum MembershipTier {
    BRONZE(4_999),
    SILVER(9_999),
    GOLD(19_999),
    PLATINUM(Integer.MAX_VALUE);

    private final int maxPoints;

    public static MembershipTier fromPoints(int points) {
        return Stream.of(values())
                .filter(tier -> points >= 0 && points <= tier.maxPoints)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Invalid value: %d.", points)));
    }
}
