package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.github.jbence1994.webshop.image.ImageTestConstants.AVATAR_FILE_NAME;
import static com.github.jbence1994.webshop.user.AddressTestObject.address;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.DATE_OF_BIRTH;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.FIRST_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.LAST_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.MIDDLE_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.PHONE_NUMBER;

public final class ProfileTestObject {
    public static Profile profileWithoutAvatar() {
        return buildProfile(1L, null, 0);
    }

    public static Profile profileWithAvatar() {
        return buildProfile(1L, AVATAR_FILE_NAME, 0);
    }

    public static Profile profileAfterMappingFromDto() {
        return buildProfile(null, null, 0);
    }

    public static Profile bronzeUser() {
        return buildProfile(1L, null, 4_999);
    }

    public static Profile silverUser() {
        return buildProfile(2L, null, 9_999);
    }

    public static Profile goldUser() {
        return buildProfile(3L, null, 50_000);
    }

    private static Profile buildProfile(
            Long userId,
            String avatarFileName,
            int loyaltyPoints
    ) {
        return new Profile(
                userId,
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
                address(),
                new ArrayList<>()
        );
    }
}
