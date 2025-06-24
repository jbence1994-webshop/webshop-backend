package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.image.ImageTestConstants.AVATAR_FILE_NAME;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static com.github.jbence1994.webshop.user.UserTestObject.userWithAvatar;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class UserTests {
    private final User user1 = user();
    private final User user2 = userWithAvatar();

    private static Stream<Arguments> userParams() {
        return Stream.of(
                Arguments.of("User with avatar", userWithAvatar(), true),
                Arguments.of("User without avatar", user(), false)
        );
    }

    @Test
    public void setProfileAvatarTest() {
        user1.setProfileAvatar(AVATAR_FILE_NAME);

        assertThat(user1.getProfileAvatar(), not(nullValue()));
    }

    @Test
    public void getProfileAvatarTest() {
        var result = user2.getProfileAvatar();

        assertThat(result, not(nullValue()));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("userParams")
    public void hasProfileAvatarTests(
            String testCase,
            User user,
            boolean expectedResult
    ) {
        var result = user.hasProfileAvatar();

        assertThat(result, is(expectedResult));
    }
}
