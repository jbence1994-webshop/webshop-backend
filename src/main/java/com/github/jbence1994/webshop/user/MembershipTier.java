package com.github.jbence1994.webshop.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MembershipTier {
    BRONZE(1_000),
    SILVER(5_000),
    GOLD(10_000),
    PLATINUM(20_000);

    private final int loyaltyPoints;
}
