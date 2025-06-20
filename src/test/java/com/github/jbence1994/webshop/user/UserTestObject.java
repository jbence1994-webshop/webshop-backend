package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.user.ProfileTestObject.platinumProfile3;
import static com.github.jbence1994.webshop.user.ProfileTestObject.bronzeProfile1;
import static com.github.jbence1994.webshop.user.UserTestConstants.ANOTHER_EMAIL;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;
import static com.github.jbence1994.webshop.user.UserTestConstants.HASHED_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.PASSWORD;

public final class UserTestObject {
    public static User user() {
        return buildUser(1L, EMAIL, HASHED_PASSWORD, Role.ADMIN, bronzeProfile1());
    }

    public static User anotherUser() {
        return buildUser(1L, ANOTHER_EMAIL, HASHED_PASSWORD, Role.USER, bronzeProfile1());
    }

    public static User userWithAvatar() {
        return buildUser(1L, EMAIL, HASHED_PASSWORD, Role.ADMIN, platinumProfile3());
    }

    public static User userAfterMappingFromDto() {
        return buildUser(null, EMAIL, PASSWORD, Role.ADMIN, null);
    }

    private static User buildUser(Long id, String email, String password, Role role, Profile profile) {
        return new User(
                id,
                email,
                password,
                role,
                LocalDateTime.now(),
                LocalDateTime.now(),
                profile,
                null
        );
    }
}
