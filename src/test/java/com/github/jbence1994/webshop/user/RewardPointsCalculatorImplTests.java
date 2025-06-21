package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.github.jbence1994.webshop.user.ProfileTestObject.bronzeProfile1;
import static com.github.jbence1994.webshop.user.ProfileTestObject.goldProfile1;
import static com.github.jbence1994.webshop.user.ProfileTestObject.platinumProfile1;
import static com.github.jbence1994.webshop.user.ProfileTestObject.silverProfile1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
public class RewardPointsCalculatorImplTests {

    @InjectMocks
    private RewardPointsCalculatorImpl rewardPointsCalculator;

    private static Stream<Arguments> orderTotalPriceParams() {
        return Stream.of(
                Arguments.of(
                        "Bronze profile, reward points: 149",
                        bronzeProfile1().getMembershipTier().getRewardPointsMultiplier(),
                        149
                ),
                Arguments.of(
                        "Silver profile, reward points: 199",
                        silverProfile1().getMembershipTier().getRewardPointsMultiplier(),
                        199
                ),
                Arguments.of(
                        "Gold profile, reward points: 249", goldProfile1().getMembershipTier().getRewardPointsMultiplier(),
                        249
                ),
                Arguments.of(
                        "Platinum profile, reward points: 499",
                        platinumProfile1().getMembershipTier().getRewardPointsMultiplier(),
                        499
                )
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("orderTotalPriceParams")
    public void calculateRewardPointsTest(
            String testCase,
            double rewardPointsMultiplier,
            int rewardPoints
    ) {
        var result = rewardPointsCalculator.calculateRewardPoints(BigDecimal.valueOf(99.99), rewardPointsMultiplier);

        assertThat(result, equalTo(rewardPoints));
    }
}
