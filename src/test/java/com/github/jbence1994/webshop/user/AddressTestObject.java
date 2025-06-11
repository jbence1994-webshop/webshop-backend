package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.AddressTestConstants.ADDRESS_LINE;
import static com.github.jbence1994.webshop.user.AddressTestConstants.COUNTRY;
import static com.github.jbence1994.webshop.user.AddressTestConstants.MUNICIPALITY;
import static com.github.jbence1994.webshop.user.AddressTestConstants.POSTAL_CODE;
import static com.github.jbence1994.webshop.user.AddressTestConstants.PROVINCE;

public final class AddressTestObject {
    public static Address address() {
        return new Address(
                1L,
                null,
                ADDRESS_LINE,
                MUNICIPALITY,
                PROVINCE,
                POSTAL_CODE,
                COUNTRY
        );
    }
}
