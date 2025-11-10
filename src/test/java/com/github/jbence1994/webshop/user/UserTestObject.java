package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.user.ProfileTestObject.bronzeProfile;
import static com.github.jbence1994.webshop.user.ProfileTestObject.goldProfile;
import static com.github.jbence1994.webshop.user.ProfileTestObject.profile2WithoutAvatar;
import static com.github.jbence1994.webshop.user.ProfileTestObject.profileWithAvatar;
import static com.github.jbence1994.webshop.user.ProfileTestObject.profileWithoutAvatar;
import static com.github.jbence1994.webshop.user.ProfileTestObject.silverProfile;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL_1;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL_2;
import static com.github.jbence1994.webshop.user.UserTestConstants.HASHED_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.PASSWORD;

public final class UserTestObject {
    public static User user1WithoutAvatar() {
        return buildUser(1L, EMAIL_1, HASHED_PASSWORD, Role.ADMIN, profileWithoutAvatar());
    }

    public static User user2WithoutAvatar() {
        return buildUser(2L, EMAIL_2, HASHED_PASSWORD, Role.USER, profile2WithoutAvatar());
    }

    public static User user1WithAvatar() {
        return buildUser(1L, EMAIL_1, HASHED_PASSWORD, Role.ADMIN, profileWithAvatar());
    }

    public static User user1AfterMappingFromDto() {
        return buildUser(null, EMAIL_1, PASSWORD, Role.ADMIN, null);
    }

    public static User user1WithBronzeProfile() {
        return buildUser(1L, EMAIL_1, HASHED_PASSWORD, Role.ADMIN, bronzeProfile());
    }

    public static User user1WithSilverProfile() {
        return buildUser(1L, EMAIL_1, HASHED_PASSWORD, Role.ADMIN, silverProfile());
    }

    public static User user1WithGoldProfile() {
        return buildUser(1L, EMAIL_1, HASHED_PASSWORD, Role.ADMIN, goldProfile());
    }

    private static User buildUser(
            Long id,
            String email,
            String password,
            Role role,
            Profile profile
    ) {
        return new User(
                id,
                email,
                password,
                role,
                LocalDateTime.now(),
                LocalDateTime.now(),
                profile
        );
    }
}
