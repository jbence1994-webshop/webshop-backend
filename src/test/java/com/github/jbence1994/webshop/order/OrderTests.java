package com.github.jbence1994.webshop.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSession1;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.FREE_SHIPPING_THRESHOLD;
import static com.github.jbence1994.webshop.order.OrderTestObject.order1;
import static com.github.jbence1994.webshop.order.OrderTestObject.order2;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

public class OrderTests {

    private static Stream<Arguments> isEligibleForFreeShippingTestParams() {
        return Stream.of(
                Arguments.of("Not eligible for free shipping", order1(), false),
                Arguments.of("Eligible for free shipping", order2(), true)
        );
    }

    @Test
    public void fromTests() {
        var result = Order.from(user(), checkoutSession1());

        assertThat(result, allOf(
                hasProperty("totalPrice", comparesEqualTo(checkoutSession1().getCartTotal())),
                hasProperty("discountAmount", comparesEqualTo(checkoutSession1().getDiscountAmount())),
                hasProperty("status", equalTo(OrderStatus.CREATED))
        ));
        assertThat(result.getItems().size(), equalTo(1));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("isEligibleForFreeShippingTestParams")
    public void isEligibleForFreeShippingTest(
            String testCase,
            Order order,
            boolean expectedResult
    ) {
        var result = order.isEligibleForFreeShipping(FREE_SHIPPING_THRESHOLD);

        assertThat(result, is(expectedResult));
    }
}
