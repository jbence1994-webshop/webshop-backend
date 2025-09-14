package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.coupon.DiscountType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CartTotalAdjustmentStrategyFactoryTests {

    private static Stream<Arguments> cartTotalAdjustmentStrategyParams() {
        return Stream.of(
                Arguments.of(
                        "Is instance of FixedAmountCartTotalAdjustmentStrategy",
                        DiscountType.FIXED_AMOUNT,
                        new FixedAmountCartTotalAdjustmentStrategy()
                ),
                Arguments.of(
                        "Is instance of PercentOffCartTotalAdjustmentStrategy",
                        DiscountType.PERCENT_OFF,
                        new PercentOffCartTotalAdjustmentStrategy()
                )
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("cartTotalAdjustmentStrategyParams")
    public void getCartTotalAdjustmentStrategyTest(
            String testCase,
            DiscountType discountType,
            CartTotalAdjustmentStrategy instance
    ) {
        var result = CartTotalAdjustmentStrategyFactory.getCartTotalAdjustmentStrategy(discountType);

        assertThat(result.getClass(), equalTo(instance.getClass()));
    }
}
