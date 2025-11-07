package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MembershipTierTests {

    private static Stream<Arguments> pointParams() {
        return Stream.of(
                Arguments.of(Named.of("0 points", 0), MembershipTier.BRONZE),
                Arguments.of(Named.of("2,500 points", 2_500), MembershipTier.BRONZE),
                Arguments.of(Named.of("4,999 points", 4_999), MembershipTier.BRONZE),
                Arguments.of(Named.of("5,000 points", 5_000), MembershipTier.SILVER),
                Arguments.of(Named.of("7,500 points", 7_500), MembershipTier.SILVER),
                Arguments.of(Named.of("9,999 points", 9_999), MembershipTier.SILVER),
                Arguments.of(Named.of("10,000 points", 10_000), MembershipTier.GOLD),
                Arguments.of(Named.of("15,000 points", 15_000), MembershipTier.GOLD),
                Arguments.of(Named.of("19,999 points", 19_999), MembershipTier.GOLD),
                Arguments.of(Named.of("20,000 points", 20_000), MembershipTier.PLATINUM),
                Arguments.of(Named.of("510,000 points", 510_000), MembershipTier.PLATINUM),
                Arguments.of(Named.of("1,000,000 points", 1_000_000), MembershipTier.PLATINUM)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("pointParams")
    public void fromPointsTest_HappyPaths(int points, MembershipTier expectedResult) {
        var result = MembershipTier.fromPoints(points);

        assertThat(result, equalTo(expectedResult));
    }

    @Test
    public void fromPointsTest_UnhappyPath() {
        var result = assertThrows(
                IllegalArgumentException.class,
                () -> MembershipTier.fromPoints(-1)
        );

        assertThat(result.getMessage(), equalTo("Invalid value: -1."));
    }
}
