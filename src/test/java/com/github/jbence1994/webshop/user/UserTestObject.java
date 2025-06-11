package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.AddressTestObject.address;
import static com.github.jbence1994.webshop.user.ProfileTestObject.bronzeProfile1;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;
import static com.github.jbence1994.webshop.user.UserTestConstants.FIRST_NAME;
import static com.github.jbence1994.webshop.user.UserTestConstants.LAST_NAME;
import static com.github.jbence1994.webshop.user.UserTestConstants.MIDDLE_NAME;
import static com.github.jbence1994.webshop.user.UserTestConstants.PHONE_NUMBER;

public final class UserTestObject {
    public static User user() {
        return new User(
                1L,
                FIRST_NAME,
                MIDDLE_NAME,
                LAST_NAME,
                EMAIL,
                PHONE_NUMBER,
                address(),
                bronzeProfile1()
        );
    }
}
