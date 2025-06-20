package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.ProfileDtoTestObject.bronzeProfileDto;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;

public final class UserDtoTestObject {
    public static UserDto userDto() {
        return new UserDto(
                1L,
                EMAIL,
                bronzeProfileDto()
        );
    }
}
