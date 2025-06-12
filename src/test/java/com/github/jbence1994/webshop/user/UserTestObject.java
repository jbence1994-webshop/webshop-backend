package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.user.ProfileTestObject.profile;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;
import static com.github.jbence1994.webshop.user.UserTestConstants.PASSWORD;

public final class UserTestObject {
    public static User user() {
        return new User(
                1L,
                EMAIL,
                PASSWORD,
                LocalDateTime.now(),
                LocalDateTime.now(),
                profile()
        );
    }
}
