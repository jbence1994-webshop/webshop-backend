package com.github.jbence1994.webshop.auth;

import com.github.jbence1994.webshop.user.Role;

import static com.github.jbence1994.webshop.user.ProfileTestConstants.FIRST_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.LAST_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.MIDDLE_NAME;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;

public final class UserIdentityTestObject {
    public static UserIdentity userIdentity() {
        return new UserIdentity(
                1L,
                EMAIL,
                FIRST_NAME,
                MIDDLE_NAME,
                LAST_NAME,
                Role.ADMIN
        );
    }
}
