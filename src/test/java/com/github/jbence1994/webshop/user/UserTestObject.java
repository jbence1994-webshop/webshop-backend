package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.loyalty.LoyaltyPoint;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import static com.github.jbence1994.webshop.loyalty.LoyaltyPointTestObject.loyaltyPoint1;
import static com.github.jbence1994.webshop.loyalty.LoyaltyPointTestObject.loyaltyPoint2;
import static com.github.jbence1994.webshop.loyalty.LoyaltyPointTestObject.loyaltyPoint3;
import static com.github.jbence1994.webshop.user.ProfileTestObject.profileWithAvatar;
import static com.github.jbence1994.webshop.user.ProfileTestObject.profileWithoutAvatar;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL_1;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL_2;
import static com.github.jbence1994.webshop.user.UserTestConstants.HASHED_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.PASSWORD;

public final class UserTestObject {
    public static User user1WithoutAvatar() {
        return buildUser(1L, EMAIL_1, HASHED_PASSWORD, Role.ADMIN, profileWithoutAvatar(), new ArrayList<>());
    }

    public static User user2WithoutAvatar() {
        return buildUser(2L, EMAIL_2, HASHED_PASSWORD, Role.USER, profileWithAvatar(), new ArrayList<>());
    }

    public static User user1WithAvatar() {
        return buildUser(1L, EMAIL_1, HASHED_PASSWORD, Role.ADMIN, profileWithAvatar(), new ArrayList<>());
    }

    public static User user1AfterMappingFromDto() {
        return buildUser(null, EMAIL_1, PASSWORD, Role.ADMIN, null, new ArrayList<>());
    }

    public static User bronzeUser() {
        return buildUser(1L, EMAIL_1, HASHED_PASSWORD, Role.ADMIN, profileWithoutAvatar(), List.of(loyaltyPoint1()));
    }

    public static User silverUser() {
        return buildUser(1L, EMAIL_1, HASHED_PASSWORD, Role.ADMIN, profileWithoutAvatar(), List.of(loyaltyPoint2()));
    }

    public static User goldUser() {
        return buildUser(1L, EMAIL_1, HASHED_PASSWORD, Role.ADMIN, profileWithoutAvatar(), List.of(loyaltyPoint3()));
    }

    private static User buildUser(
            Long id,
            String email,
            String password,
            Role role,
            Profile profile,
            List<LoyaltyPoint> loyaltyPoints
    ) {
        return new User(
                id,
                email,
                password,
                role,
                LocalDateTime.now(),
                LocalDateTime.now(),
                profile,
                loyaltyPoints
        );
    }
}
