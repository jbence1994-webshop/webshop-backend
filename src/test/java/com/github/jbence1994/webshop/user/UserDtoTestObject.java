package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.AddressDtoTestObject.addressDto;
import static com.github.jbence1994.webshop.user.UserTestConstants.DATE_OF_BIRTH;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL_1;
import static com.github.jbence1994.webshop.user.UserTestConstants.FIRST_NAME;
import static com.github.jbence1994.webshop.user.UserTestConstants.LAST_NAME;
import static com.github.jbence1994.webshop.user.UserTestConstants.MIDDLE_NAME;
import static com.github.jbence1994.webshop.user.UserTestConstants.PHONE_NUMBER;

public final class UserDtoTestObject {
    public static UserDto userDto() {
        return new UserDto(
                1L,
                EMAIL_1,
                FIRST_NAME,
                MIDDLE_NAME,
                LAST_NAME,
                DATE_OF_BIRTH,
                PHONE_NUMBER,
                addressDto()
        );
    }
}
