package com.github.jbence1994.webshop.order;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.FREE_SHIPPING_THRESHOLD;
import static com.github.jbence1994.webshop.order.OrderTestObject.createdOrder1;
import static com.github.jbence1994.webshop.order.OrderTestObject.createdOrder2;
import static com.github.jbence1994.webshop.user.EncryptedUserTestConstants.ENCRYPTED_EMAIL_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OrderTests {

    private static Stream<Arguments> customerEmailParams() {
        return Stream.of(
                Arguments.of(Named.of(ENCRYPTED_EMAIL_1, ENCRYPTED_EMAIL_1), true),
                Arguments.of(Named.of("example@gmail.com", "example@gmail.com"), false)
        );
    }

    private static Stream<Arguments> isEligibleForFreeShippingTestParams() {
        return Stream.of(
                Arguments.of(Named.of("Not eligible for free shipping", createdOrder1()), false),
                Arguments.of(Named.of("Eligible for free shipping", createdOrder2()), true)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("customerEmailParams")
    public void isPlacesByTests(String customerEmail, boolean expectedResult) {
        var result = createdOrder1().isPlacedBy(customerEmail);

        assertThat(result, is(expectedResult));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("isEligibleForFreeShippingTestParams")
    public void isEligibleForFreeShippingTest(Order order, boolean expectedResult) {
        var result = order.isEligibleForFreeShipping(FREE_SHIPPING_THRESHOLD);

        assertThat(result, is(expectedResult));
    }
}
