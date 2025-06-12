package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.AddressTestObject.address;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.FIRST_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.LAST_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.MIDDLE_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.PHONE_NUMBER;

public final class ProfileTestObject {
    public static Profile profile() {
        return new Profile(
                1L,
                null,
                FIRST_NAME,
                MIDDLE_NAME,
                LAST_NAME,
                PHONE_NUMBER,
                address()
        );
    }
}
