package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.coupon.DiscountType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutPriceAdjustmentStrategyFactoryTests {

    private static Stream<Arguments> checkoutPriceAdjustmentStrategyParams() {
        return Stream.of(
                Arguments.of(
                        "Is instance of FixedAmountCheckoutPriceAdjustmentStrategy",
                        DiscountType.FIXED_AMOUNT,
                        new FixedAmountCheckoutPriceAdjustmentStrategy()
                ),
                Arguments.of(
                        "Is instance of PercentOffCheckoutPriceAdjustmentStrategy",
                        DiscountType.PERCENT_OFF,
                        new PercentOffCheckoutPriceAdjustmentStrategy()
                )
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("checkoutPriceAdjustmentStrategyParams")
    public void getCheckoutPriceAdjustmentStrategyTest(
            String testCase,
            DiscountType discountType,
            CheckoutPriceAdjustmentStrategy strategyInstance
    ) {
        var result = CheckoutPriceAdjustmentStrategyFactory.getCheckoutPriceAdjustmentStrategy(discountType);

        assertThat(result.getClass(), equalTo(strategyInstance.getClass()));
    }
}
