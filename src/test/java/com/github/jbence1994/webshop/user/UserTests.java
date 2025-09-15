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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class UserTests {
    private final User user1 = user();
    private final User user2 = userWithAvatar();

    private static Stream<Arguments> getProfileAvatarTestParams() {
        return Stream.of(
                Arguments.of("User with avatar", userWithAvatar(), true, false),
                Arguments.of("User without avatar", user(), false, true)
        );
    }

    @Test
    public void getFirstNameTest() {
        var result = user1.getFirstName();

        assertThat(result, equalTo("Bence"));
    }

    @Test
    public void getPhoneNumberTest() {
        var result = user1.getPhoneNumber();

        assertThat(result, equalTo("+36501323566"));
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
    @MethodSource("getProfileAvatarTestParams")
    public void getProfileAvatarTests(
            String testCase,
            User user,
            boolean isPresent,
            boolean isEmpty
    ) {
        var result = user.getProfileAvatar();

        assertThat(result.isPresent(), is(isPresent));
        assertThat(result.isEmpty(), is(isEmpty));
    }

    @Test
    public void earnLoyaltyPointsTest() {
        user1.earnLoyaltyPoints(100);

        assertThat(100, equalTo(user1.getProfile().getLoyaltyPoints()));
    }
}
