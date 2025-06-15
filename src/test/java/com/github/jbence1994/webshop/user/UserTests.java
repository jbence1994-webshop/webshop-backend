package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;

import static com.github.jbence1994.webshop.image.ImageTestConstants.AVATAR_FILE_NAME;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class UserTests {
    private final User userWithoutAvatar = user();

    @Test
    public void setProfileAvatarTest() {
        userWithoutAvatar.setProfileAvatar(AVATAR_FILE_NAME);

        assertThat(userWithoutAvatar.getProfile().getAvatarFileName(), not(nullValue()));
    }
}
