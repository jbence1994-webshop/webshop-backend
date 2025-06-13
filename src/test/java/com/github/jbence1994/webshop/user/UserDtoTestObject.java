package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.user.ProfileDtoTestObject.profileDto;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;

public final class UserDtoTestObject {
    public static UserDto userDto() {
        return new UserDto(
                1L,
                EMAIL,
                profileDto(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
