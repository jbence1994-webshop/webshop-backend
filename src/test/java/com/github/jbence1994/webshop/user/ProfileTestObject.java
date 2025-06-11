package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.ProfileTestConstants.PASSWORD;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.USERNAME;

public final class ProfileTestObject {
    public static Profile bronzeProfile1() {
        return buildProfile(0);
    }

    public static Profile bronzeProfile2() {
        return buildProfile(2_500);
    }

    public static Profile bronzeProfile3() {
        return buildProfile(4_999);
    }

    public static Profile silverProfile1() {
        return buildProfile(5_000);
    }

    public static Profile silverProfile2() {
        return buildProfile(7_500);
    }

    public static Profile silverProfile3() {
        return buildProfile(9_999);
    }

    public static Profile goldProfile1() {
        return buildProfile(10_000);
    }

    public static Profile goldProfile2() {
        return buildProfile(15_000);
    }

    public static Profile goldProfile3() {
        return buildProfile(19_999);
    }

    public static Profile platinumProfile1() {
        return buildProfile(20_000);
    }

    public static Profile platinumProfile2() {
        return buildProfile(510_000);
    }

    public static Profile platinumProfile3() {
        return buildProfile(1_000_000);
    }

    private static Profile buildProfile(int loyaltyPoints) {
        return new Profile(
                1L,
                null,
                USERNAME,
                PASSWORD,
                loyaltyPoints
        );
    }
}
