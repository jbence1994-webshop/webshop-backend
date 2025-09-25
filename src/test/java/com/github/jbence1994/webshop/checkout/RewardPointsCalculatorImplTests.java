package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.user.MembershipTier;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
public class RewardPointsCalculatorImplTests {

    @InjectMocks
    private RewardPointsCalculatorImpl rewardPointsCalculator;

    private static Stream<Arguments> rewardPointsParams() {
        return Stream.of(
                Arguments.of("Multiplier is 1.5", MembershipTier.BRONZE.getRewardPointsMultiplier(), 74),
                Arguments.of("Multiplier is 2", MembershipTier.SILVER.getRewardPointsMultiplier(), 99),
                Arguments.of("Multiplier is 2.5", MembershipTier.GOLD.getRewardPointsMultiplier(), 124),
                Arguments.of("Multiplier is 5", MembershipTier.PLATINUM.getRewardPointsMultiplier(), 249)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("rewardPointsParams")
    public void calculateRewardPointsTests(
            String testCase,
            double multiplier,
            int expectedEarnedRewardPoints
    ) {
        var result = rewardPointsCalculator.calculateRewardPoints(
                BigDecimal.valueOf(49.99),
                multiplier
        );

        assertThat(result, equalTo(expectedEarnedRewardPoints));
    }
}
