package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.user.MembershipTier;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoyaltyCalculationStrategyFactoryTests {

    private static Stream<Arguments> loyaltyCalculationStrategyParams() {
        return Stream.of(
                Arguments.of(
                        Named.of("Is instance of BronzeCalculationStrategy", MembershipTier.BRONZE),
                        new BronzeCalculationStrategy()
                ),
                Arguments.of(
                        Named.of("Is instance of SilverCalculationStrategy", MembershipTier.SILVER),
                        new SilverCalculationStrategy()
                ),
                Arguments.of(
                        Named.of("Is instance of GoldCalculationStrategy", MembershipTier.GOLD),
                        new GoldCalculationStrategy()
                )
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("loyaltyCalculationStrategyParams")
    public void getLoyaltyCalculationStrategyTests(MembershipTier tier, LoyaltyCalculationStrategy instance) {
        var result = LoyaltyCalculationStrategyFactory.getLoyaltyCalculationStrategy(tier);

        assertThat(result.getClass(), equalTo(instance.getClass()));
    }
}
