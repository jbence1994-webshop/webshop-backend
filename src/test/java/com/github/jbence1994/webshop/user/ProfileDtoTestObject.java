package com.github.jbence1994.webshop.user;

import java.util.ArrayList;

import static com.github.jbence1994.webshop.user.AddressDtoTestObject.addressDto;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.DATE_OF_BIRTH;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.FIRST_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.LAST_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.MIDDLE_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.PHONE_NUMBER;

public final class ProfileDtoTestObject {
    public static ProfileDto bronzeProfileDto() {
        return new ProfileDto(
                FIRST_NAME,
                MIDDLE_NAME,
                LAST_NAME,
                DATE_OF_BIRTH,
                PHONE_NUMBER,
                0,
                0,
                MembershipTier.BRONZE.name(),
                addressDto(),
                new ArrayList<>()
        );
    }
}
