package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.user.MembershipTier;
import org.junit.jupiter.api.Named;
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
public class RewardPointsConverterImplTests {

    @InjectMocks
    private RewardPointsConverterImpl rewardPointsConverter;

    private static Stream<Arguments> rewardPointsParams() {
        return Stream.of(
                Arguments.of(Named.of("Multiplier is 1.5", MembershipTier.BRONZE), 74),
                Arguments.of(Named.of("Multiplier is 2", MembershipTier.SILVER), 99),
                Arguments.of(Named.of("Multiplier is 2.5", MembershipTier.GOLD), 124),
                Arguments.of(Named.of("Multiplier is 5", MembershipTier.PLATINUM), 249)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("rewardPointsParams")
    public void toRewardPointsTests(MembershipTier membershipTier, int expectedRewardPoints) {
        var result = rewardPointsConverter.toRewardPoints(BigDecimal.valueOf(49.99), membershipTier);

        assertThat(result, equalTo(expectedRewardPoints));
    }
}
