package com.github.jbence1994.webshop.user;

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
public class LoyaltyPointsCalculator20Tests {

    @InjectMocks
    private LoyaltyPointsCalculator20 loyaltyPointsCalculator;

    private static Stream<Arguments> orderTotalPriceParams() {
        return Stream.of(
                Arguments.of("Order total price: $19.99, loyalty points: 0", BigDecimal.valueOf(19.99), 0),
                Arguments.of("Order total price: $20, loyalty points: 1", BigDecimal.valueOf(20.00), 1),
                Arguments.of("Order total price: $39.99, loyalty points: 1", BigDecimal.valueOf(39.99), 1),
                Arguments.of("Order total price: $40, loyalty points: 2", BigDecimal.valueOf(40.00), 2),
                Arguments.of("Order total price: $59.99, loyalty points: 2", BigDecimal.valueOf(59.99), 2),
                Arguments.of("Order total price: $60, loyalty points: 3", BigDecimal.valueOf(60.00), 3),
                Arguments.of("Order total price: $79.99, loyalty points: 3", BigDecimal.valueOf(79.99), 3)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("orderTotalPriceParams")
    public void calculateLoyaltyPointsTest(
            String testCase,
            BigDecimal orderTotalPrice,
            int loyaltyPoints
    ) {
        var result = loyaltyPointsCalculator.calculateLoyaltyPoints(orderTotalPrice);

        assertThat(result, equalTo(loyaltyPoints));
    }
}
