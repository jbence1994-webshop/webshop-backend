package com.github.jbence1994.webshop.loyalty;

import java.time.LocalDateTime;

public final class LoyaltyPointTestObject {
    public static LoyaltyPoint loyaltyPoint1() {
        return buildLoyaltyPoint(1L, 4_999);
    }

    public static LoyaltyPoint loyaltyPoint2() {
        return buildLoyaltyPoint(2L, 9_999);
    }

    public static LoyaltyPoint loyaltyPoint3() {
        return buildLoyaltyPoint(3L, 50_000);
    }

    private static LoyaltyPoint buildLoyaltyPoint(Long id, Integer amount) {
        return new LoyaltyPoint(
                id,
                null,
                null,
                amount,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(365)
        );
    }
}
