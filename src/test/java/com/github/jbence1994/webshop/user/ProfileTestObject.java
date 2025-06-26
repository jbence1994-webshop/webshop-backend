package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.image.ImageTestConstants.AVATAR_FILE_NAME;
import static com.github.jbence1994.webshop.user.AddressTestObject.address;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.DATE_OF_BIRTH;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.FIRST_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.LAST_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.MIDDLE_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.PHONE_NUMBER;

public final class ProfileTestObject {
    public static Profile bronzeProfile1() {
        return buildProfile(null, 0);
    }

    public static Profile bronzeProfile2() {
        return buildProfile(null, 2_500);
    }

    public static Profile bronzeProfile3() {
        return buildProfile(null, 4_999);
    }

    public static Profile silverProfile1() {
        return buildProfile(null, 5_000);
    }

    public static Profile silverProfile2() {
        return buildProfile(null, 7_500);
    }

    public static Profile silverProfile3() {
        return buildProfile(null, 9_999);
    }

    public static Profile goldProfile1() {
        return buildProfile(null, 10_000);
    }

    public static Profile goldProfile2() {
        return buildProfile(null, 15_000);
    }

    public static Profile goldProfile3() {
        return buildProfile(null, 19_999);
    }

    public static Profile platinumProfile1() {
        return buildProfile(null, 20_000);
    }

    public static Profile platinumProfile2() {
        return buildProfile(null, 2_550_000);
    }

    public static Profile platinumProfile3() {
        return buildProfile(AVATAR_FILE_NAME, 1_000_000);
    }

    private static Profile buildProfile(String avatarFileName, int loyaltyPoints) {
        return new Profile(
                1L,
                null,
                FIRST_NAME,
                MIDDLE_NAME,
                LAST_NAME,
                DATE_OF_BIRTH,
                PHONE_NUMBER,
                avatarFileName,
                loyaltyPoints,
                LocalDateTime.now(),
                LocalDateTime.now(),
                address()
        );
    }
}
