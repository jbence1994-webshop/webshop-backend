package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2;
import static com.github.jbence1994.webshop.user.ProfileTestObject.bronzeUser;
import static com.github.jbence1994.webshop.user.ProfileTestObject.goldUser;
import static com.github.jbence1994.webshop.user.ProfileTestObject.profileWithoutAvatar;
import static com.github.jbence1994.webshop.user.ProfileTestObject.silverUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProfileTests {
    private final Profile profile = profileWithoutAvatar();

    private static Stream<Arguments> profileMembershipTierTestParams() {
        return Stream.of(
                Arguments.of(Named.of("BRONZE user", bronzeUser()), MembershipTier.BRONZE),
                Arguments.of(Named.of("SILVER user", silverUser()), MembershipTier.SILVER),
                Arguments.of(Named.of("GOLD user", goldUser()), MembershipTier.GOLD)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("profileMembershipTierTestParams")
    public void getMembershipTierTests(Profile profile, MembershipTier expectedMembershipTier) {
        var result = profile.getMembershipTier();

        assertThat(result, equalTo(expectedMembershipTier));
    }

    @Test
    public void removeFavoriteProductTest() {
        profile.addFavoriteProduct(product1());
        profile.addFavoriteProduct(product2());

        profile.removeFavoriteProduct(1L);

        assertThat(profile.getFavoriteProducts().size(), equalTo(1));
    }
}
