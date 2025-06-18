package com.github.jbence1994.webshop.cart;

import com.github.jbence1994.webshop.coupon.DiscountType;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
public class PriceAdjustmentStrategyFactoryTests {

    @InjectMocks
    private PriceAdjustmentStrategyFactory priceAdjustmentStrategyFactory;

    private static Stream<Arguments> priceAdjustmentStrategyParams() {
        return Stream.of(
                Arguments.of(
                        "Is instance of FixedAmountPriceAdjustmentStrategy",
                        DiscountType.FIXED_AMOUNT,
                        new FixedAmountPriceAdjustmentStrategy()
                ),
                Arguments.of(
                        "Is instance of PercentOffPriceAdjustmentStrategy",
                        DiscountType.PERCENT_OFF,
                        new PercentOffPriceAdjustmentStrategy()
                )
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("priceAdjustmentStrategyParams")
    public void getPriceAdjustmentStrategyTest(
            String testCase,
            DiscountType discountType,
            PriceAdjustmentStrategy strategyInstance
    ) {
        var result = priceAdjustmentStrategyFactory.getPriceAdjustmentStrategy(discountType);

        assertThat(result.getClass(), equalTo(strategyInstance.getClass()));
    }
}
