package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.loyalty.LoyaltyPoint;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.github.jbence1994.webshop.image.ImageTestConstants.AVATAR_FILE_NAME;
import static com.github.jbence1994.webshop.loyalty.LoyaltyPointTestObject.loyaltyPoint1;
import static com.github.jbence1994.webshop.loyalty.LoyaltyPointTestObject.loyaltyPoint2;
import static com.github.jbence1994.webshop.loyalty.LoyaltyPointTestObject.loyaltyPoint3;
import static com.github.jbence1994.webshop.user.AddressTestObject.address;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.DATE_OF_BIRTH;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.FIRST_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.LAST_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.MIDDLE_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.PHONE_NUMBER;

public final class ProfileTestObject {
    public static Profile profileWithoutAvatar() {
        return buildProfile(1L, null, new ArrayList<>());
    }

    public static Profile profile2WithoutAvatar() {
        return buildProfile(2L, null, new ArrayList<>());
    }

    public static Profile profileWithAvatar() {
        return buildProfile(1L, AVATAR_FILE_NAME, new ArrayList<>());
    }

    public static Profile profileAfterMappingFromDto() {
        return buildProfile(null, null, new ArrayList<>());
    }

    public static Profile bronzeProfile() {
        return buildProfile(1L, null, List.of(loyaltyPoint1()));
    }

    public static Profile silverProfile() {
        return buildProfile(1L, null, List.of(loyaltyPoint2()));
    }

    public static Profile goldProfile() {
        return buildProfile(1L, null, List.of(loyaltyPoint3()));
    }

    private static Profile buildProfile(
            Long userId,
            String avatarFileName,
            List<LoyaltyPoint> loyaltyPoints
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
                LocalDateTime.now(),
                LocalDateTime.now(),
                address(),
                new ArrayList<>(),
                loyaltyPoints,
                new ArrayList<>()
        );
    }
}
