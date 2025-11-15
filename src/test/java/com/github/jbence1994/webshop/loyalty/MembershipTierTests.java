package com.github.jbence1994.webshop.loyalty;

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
                Arguments.of(Named.of("0 point", 0), MembershipTier.BRONZE),
                Arguments.of(Named.of("4,999 points", 4_999), MembershipTier.BRONZE),
                Arguments.of(Named.of("5,000 points", 5_000), MembershipTier.SILVER),
                Arguments.of(Named.of("9,999 points", 9_999), MembershipTier.SILVER),
                Arguments.of(Named.of("10,000 points", 10_000), MembershipTier.GOLD),
                Arguments.of(Named.of("50,000 points", 50_000), MembershipTier.GOLD)
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
