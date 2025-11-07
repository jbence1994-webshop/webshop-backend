package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2;
import static com.github.jbence1994.webshop.user.ProfileTestObject.profileWithAvatar;
import static com.github.jbence1994.webshop.user.ProfileTestObject.profileWithoutAvatar;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ProfileTests {
    private final Profile profile = profileWithoutAvatar();

    private static Stream<Arguments> profileAvatarTestParams() {
        return Stream.of(
                Arguments.of(Named.of("Profile with avatar", profileWithAvatar()), true, false),
                Arguments.of(Named.of("Profile without avatar", profileWithoutAvatar()), false, true)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("profileAvatarTestParams")
    public void getProfileAvatarTests(Profile profile, boolean isPresent, boolean isEmpty) {
        var result = profile.getProfileAvatar();

        assertThat(result.isPresent(), is(isPresent));
        assertThat(result.isEmpty(), is(isEmpty));
    }

    @Test
    public void addFavoriteProductTest() {
        profile.addFavoriteProduct(product1());

        assertThat(profile.getFavoriteProducts().size(), equalTo(1));
    }

    @Test
    public void removeFavoriteProductTest() {
        profile.addFavoriteProduct(product1());
        profile.addFavoriteProduct(product2());

        profile.removeFavoriteProduct(1L);

        assertThat(profile.getFavoriteProducts().size(), equalTo(1));
    }
}
