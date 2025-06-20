package com.github.jbence1994.webshop.user;

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
                Arguments.of("0", 0, MembershipTier.BRONZE),
                Arguments.of("2_500", 2_500, MembershipTier.BRONZE),
                Arguments.of("4_999", 4_999, MembershipTier.BRONZE),
                Arguments.of("5_000", 5_000, MembershipTier.SILVER),
                Arguments.of("7_500", 7_500, MembershipTier.SILVER),
                Arguments.of("9_999", 9_999, MembershipTier.SILVER),
                Arguments.of("10_000", 10_000, MembershipTier.GOLD),
                Arguments.of("15_000", 15_000, MembershipTier.GOLD),
                Arguments.of("19_999", 19_999, MembershipTier.GOLD),
                Arguments.of("20_000", 20_000, MembershipTier.PLATINUM),
                Arguments.of("510_000", 510_000, MembershipTier.PLATINUM),
                Arguments.of("1_000_000", 1_000_000, MembershipTier.PLATINUM)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("pointParams")
    public void fromPointsTest_HappyPaths(
            String testCase,
            int points,
            MembershipTier expectedResult
    ) {
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
