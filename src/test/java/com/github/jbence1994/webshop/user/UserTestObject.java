package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.user.ProfileTestObject.platinumProfile3;
import static com.github.jbence1994.webshop.user.ProfileTestObject.bronzeProfile1;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;
import static com.github.jbence1994.webshop.user.UserTestConstants.HASHED_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.PASSWORD;

public final class UserTestObject {
    public static User user() {
        return buildUser(1L, HASHED_PASSWORD, bronzeProfile1());
    }

    public static User userWithAvatar() {
        return buildUser(1L, HASHED_PASSWORD, platinumProfile3());
    }

    public static User userAfterMappingFromDto() {
        return buildUser(null, PASSWORD, null);
    }

    private static User buildUser(Long id, String password, Profile profile) {
        return new User(
                id,
                EMAIL,
                password,
                LocalDateTime.now(),
                LocalDateTime.now(),
                profile
        );
    }
}
