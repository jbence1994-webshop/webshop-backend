package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.ProfileDtoTestObject.profileDto;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL_1;

public final class UserDtoTestObject {
    public static UserDto userDto() {
        return new UserDto(
                1L,
                EMAIL_1,
                profileDto(),
                MembershipTier.BRONZE.name(),
                0
        );
    }
}
