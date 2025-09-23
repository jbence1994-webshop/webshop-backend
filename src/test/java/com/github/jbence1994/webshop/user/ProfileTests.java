package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2;
import static com.github.jbence1994.webshop.user.MembershipTier.BRONZE;
import static com.github.jbence1994.webshop.user.MembershipTier.GOLD;
import static com.github.jbence1994.webshop.user.MembershipTier.PLATINUM;
import static com.github.jbence1994.webshop.user.MembershipTier.SILVER;
import static com.github.jbence1994.webshop.user.ProfileTestObject.bronzeProfile1;
import static com.github.jbence1994.webshop.user.ProfileTestObject.bronzeProfile2;
import static com.github.jbence1994.webshop.user.ProfileTestObject.bronzeProfile3;
import static com.github.jbence1994.webshop.user.ProfileTestObject.goldProfile1;
import static com.github.jbence1994.webshop.user.ProfileTestObject.goldProfile2;
import static com.github.jbence1994.webshop.user.ProfileTestObject.goldProfile3;
import static com.github.jbence1994.webshop.user.ProfileTestObject.platinumProfile1;
import static com.github.jbence1994.webshop.user.ProfileTestObject.platinumProfile2;
import static com.github.jbence1994.webshop.user.ProfileTestObject.platinumProfile3;
import static com.github.jbence1994.webshop.user.ProfileTestObject.silverProfile1;
import static com.github.jbence1994.webshop.user.ProfileTestObject.silverProfile2;
import static com.github.jbence1994.webshop.user.ProfileTestObject.silverProfile3;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProfileTests {
    private final Profile profile = bronzeProfile1();

    private static Stream<Arguments> profileParams() {
        return Stream.of(
                Arguments.of(String.format("%s profile with %d loyalty points", BRONZE.name(), bronzeProfile1().getLoyaltyPoints()), bronzeProfile1(), BRONZE),
                Arguments.of(String.format("%s profile with %d loyalty points", BRONZE.name(), bronzeProfile2().getLoyaltyPoints()), bronzeProfile2(), BRONZE),
                Arguments.of(String.format("%s profile with %d loyalty points", BRONZE.name(), bronzeProfile3().getLoyaltyPoints()), bronzeProfile3(), BRONZE),
                Arguments.of(String.format("%s profile with %d loyalty points", SILVER.name(), silverProfile1().getLoyaltyPoints()), silverProfile1(), SILVER),
                Arguments.of(String.format("%s profile with %d loyalty points", SILVER.name(), silverProfile2().getLoyaltyPoints()), silverProfile2(), SILVER),
                Arguments.of(String.format("%s profile with %d loyalty points", SILVER.name(), silverProfile3().getLoyaltyPoints()), silverProfile3(), SILVER),
                Arguments.of(String.format("%s profile with %d loyalty points", GOLD.name(), goldProfile1().getLoyaltyPoints()), goldProfile1(), GOLD),
                Arguments.of(String.format("%s profile with %d loyalty points", GOLD.name(), goldProfile2().getLoyaltyPoints()), goldProfile2(), GOLD),
                Arguments.of(String.format("%s profile with %d loyalty points", GOLD.name(), goldProfile3().getLoyaltyPoints()), goldProfile3(), GOLD),
                Arguments.of(String.format("%s profile with %d loyalty points", PLATINUM.name(), platinumProfile1().getLoyaltyPoints()), platinumProfile1(), PLATINUM),
                Arguments.of(String.format("%s profile with %d loyalty points", PLATINUM.name(), platinumProfile2().getLoyaltyPoints()), platinumProfile2(), PLATINUM),
                Arguments.of(String.format("%s profile with %d loyalty points", PLATINUM.name(), platinumProfile3().getLoyaltyPoints()), platinumProfile3(), PLATINUM)
        );
    }

    @Test
    public void earnLoyaltyPointsTest() {
        profile.earnLoyaltyPoints(100);

        assertThat(profile.getLoyaltyPoints(), equalTo(100));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("profileParams")
    public void getMembershipTierTests(
            String testCase,
            Profile profile,
            MembershipTier expectedMembershipTier
    ) {
        var result = profile.getMembershipTier();

        assertThat(result, equalTo(expectedMembershipTier));
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
