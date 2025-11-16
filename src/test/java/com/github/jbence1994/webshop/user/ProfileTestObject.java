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
    public static Profile profileWithoutAvatar() {
        return buildProfile(1L, null);
    }

    public static Profile profileWithAvatar() {
        return buildProfile(1L, AVATAR_FILE_NAME);
    }

    public static Profile profileAfterMappingFromDto() {
        return buildProfile(null, null);
    }

    private static Profile buildProfile(Long userId, String avatarFileName) {
        return new Profile(
                userId,
                null,
                FIRST_NAME,
                MIDDLE_NAME,
                LAST_NAME,
                DATE_OF_BIRTH,
                PHONE_NUMBER,
                avatarFileName,
                LocalDateTime.now(),
                LocalDateTime.now(),
                address()
        );
    }
}
