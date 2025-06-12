package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.ProfileTestConstants.PASSWORD;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.USERNAME;

public final class ProfileTestObject {
    public static Profile profile() {
        return new Profile(
                1L,
                null,
                USERNAME,
                PASSWORD
        );
    }
}
