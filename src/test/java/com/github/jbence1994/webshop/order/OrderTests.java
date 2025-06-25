package com.github.jbence1994.webshop.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithTwoItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class OrderTests {

    private static Stream<Arguments> calculateLoyaltyPointsTestParams() {
        return Stream.of(
                Arguments.of("Total price: $19.99, loyalty points: 0", BigDecimal.valueOf(19.99), 0),
                Arguments.of("Total price: $20, loyalty points: 1", BigDecimal.valueOf(20.00), 1),
                Arguments.of("Total price: $39.99, loyalty points: 1", BigDecimal.valueOf(39.99), 1),
                Arguments.of("Total price: $40, loyalty points: 2", BigDecimal.valueOf(40.00), 2),
                Arguments.of("Total price: $59.99, loyalty points: 2", BigDecimal.valueOf(59.99), 2),
                Arguments.of("Total price: $60, loyalty points: 3", BigDecimal.valueOf(60.00), 3),
                Arguments.of("Total price: $79.99, loyalty points: 3", BigDecimal.valueOf(79.99), 3)
        );
    }

    private static Stream<Arguments> calculateRewardPointsTestParams() {
        return Stream.of(
                Arguments.of(
                        "Total price: $20, tier multiplier: x1.5, reward points: 30",
                        BigDecimal.valueOf(20.00), 1.5, 30
                ),
                Arguments.of(
                        "Total price: $20, tier multiplier: x2, reward points: 40",
                        BigDecimal.valueOf(20.00), 2, 40
                ),
                Arguments.of(
                        "Total price: $20, tier multiplier: x2.5, reward points: 50",
                        BigDecimal.valueOf(20.00), 2.5, 50
                ),
                Arguments.of(
                        "Total price: $20, tier multiplier: x5, reward points: 100",
                        BigDecimal.valueOf(20.00), 5, 100
                )
        );
    }

    @Test
    public void fromTest() {
        var result = Order.from(cartWithTwoItems());

        assertThat(result, not(nullValue()));
        assertThat(result.getItems().size(), equalTo(2));
        assertThat(result, allOf(
                hasProperty("totalPrice", equalTo(BigDecimal.valueOf(139.98))),
                hasProperty("status", equalTo(OrderStatus.PENDING))
        ));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("calculateLoyaltyPointsTestParams")
    public void calculateLoyaltyPointsTest(
            String testCase,
            BigDecimal totalPrice,
            int expectedLoyaltyPoints
    ) {
        var order = new Order();
        order.setTotalPrice(totalPrice);

        var result = order.calculateLoyaltyPoints();

        assertThat(result, equalTo(expectedLoyaltyPoints));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("calculateRewardPointsTestParams")
    public void calculateRewardPointsTest(
            String testCase,
            BigDecimal totalPrice,
            double tierMultiplier,
            int expectedRewardPoints
    ) {
        var order = new Order();
        order.setTotalPrice(totalPrice);

        var result = order.calculateRewardPoints(tierMultiplier);

        assertThat(result, equalTo(expectedRewardPoints));
    }

    @Test
    public void calculateTotalAmountTest() {
        var order = new Order();
        order.setTotalPrice(BigDecimal.valueOf(49.99));
        order.setPayableTotalPrice(BigDecimal.valueOf(49.99));
        order.setShippingCost(BigDecimal.valueOf(20.00));

        var result = order.calculateTotalAmount();

        assertThat(result, equalTo(BigDecimal.valueOf(69.99)));
    }
}
