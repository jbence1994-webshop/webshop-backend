package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.image.ImageTestConstants.AVATAR_FILE_NAME;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2;
import static com.github.jbence1994.webshop.user.UserTestObject.user1WithAvatar;
import static com.github.jbence1994.webshop.user.UserTestObject.user1WithoutAvatar;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class UserTests {
    private final User user1 = user1WithoutAvatar();
    private final User user2 = user1WithAvatar();

    private static Stream<Arguments> getProfileAvatarTestParams() {
        return Stream.of(
                Arguments.of(Named.of("User with avatar", user1WithAvatar()), true, false),
                Arguments.of(Named.of("User without avatar", user1WithoutAvatar()), false, true)
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
    public void getProfileAvatarTests(User user, boolean isPresent, boolean isEmpty) {
        var result = user.getProfileAvatar();

        assertThat(result.isPresent(), is(isPresent));
        assertThat(result.isEmpty(), is(isEmpty));
    }

    @Test
    public void addFavoriteProductTest() {
        user1.addFavoriteProduct(product1());

        assertThat(user1.getProfile().getFavoriteProducts().size(), equalTo(1));
    }

    @Test
    public void removeFavoriteProductTest() {
        user1.addFavoriteProduct(product1());
        user1.addFavoriteProduct(product2());

        user1.removeFavoriteProduct(1L);

        assertThat(user1.getProfile().getFavoriteProducts().size(), equalTo(1));
    }
}
