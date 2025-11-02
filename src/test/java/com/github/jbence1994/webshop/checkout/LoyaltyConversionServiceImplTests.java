package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.user.MembershipTier;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.github.jbence1994.webshop.checkout.LoyaltyTestConstants.LOYALTY_POINTS_RATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoyaltyConversionServiceImplTests {

    @Mock
    private LoyaltyPointsConfig loyaltyPointsConfig;

    @InjectMocks
    private LoyaltyConversionServiceImpl loyaltyConversionService;

    private static Stream<Arguments> calculateLoyaltyPointsTestParams() {
        return Stream.of(
                Arguments.of(Named.of("Order total price: $19.99, loyalty points: 0", BigDecimal.valueOf(19.99)), 0),
                Arguments.of(Named.of("Order total price: $20, loyalty points: 1", BigDecimal.valueOf(20.00)), 1),
                Arguments.of(Named.of("Order total price: $39.99, loyalty points: 1", BigDecimal.valueOf(39.99)), 1),
                Arguments.of(Named.of("Order total price: $40, loyalty points: 2", BigDecimal.valueOf(40.00)), 2),
                Arguments.of(Named.of("Order total price: $59.99, loyalty points: 2", BigDecimal.valueOf(59.99)), 2),
                Arguments.of(Named.of("Order total price: $60, loyalty points: 3", BigDecimal.valueOf(60.00)), 3),
                Arguments.of(Named.of("Order total price: $79.99, loyalty points: 3", BigDecimal.valueOf(79.99)), 3)
        );
    }

    private static Stream<Arguments> rewardPointsParams() {
        return Stream.of(
                Arguments.of(Named.of("Multiplier is 1.5", MembershipTier.BRONZE), 74),
                Arguments.of(Named.of("Multiplier is 2", MembershipTier.SILVER), 99),
                Arguments.of(Named.of("Multiplier is 2.5", MembershipTier.GOLD), 124),
                Arguments.of(Named.of("Multiplier is 5", MembershipTier.PLATINUM), 249)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("calculateLoyaltyPointsTestParams")
    public void calculateLoyaltyPointsTest(BigDecimal orderTotalPrice, int expectedLoyaltyPoints) {
        when(loyaltyPointsConfig.rate()).thenReturn(LOYALTY_POINTS_RATE);

        var result = loyaltyConversionService.calculateLoyaltyPoints(orderTotalPrice);

        assertThat(result, equalTo(expectedLoyaltyPoints));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("rewardPointsParams")
    public void calculateRewardPointsTests(MembershipTier membershipTier, int expectedRewardPoints) {
        var result = loyaltyConversionService.calculateRewardPoints(BigDecimal.valueOf(49.99), membershipTier);

        assertThat(result, equalTo(expectedRewardPoints));
    }
}
