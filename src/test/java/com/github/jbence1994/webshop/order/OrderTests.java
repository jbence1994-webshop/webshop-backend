package com.github.jbence1994.webshop.order;

import com.github.jbence1994.webshop.checkout.CheckoutPrice;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithTwoItems;
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
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class OrderTests {

    private static Stream<Arguments> orderFromParams() {
        return Stream.of(
                Arguments.of(
                        "With discount",
                        CheckoutPrice.of(BigDecimal.valueOf(44.99), BigDecimal.valueOf(5.00))
                ),
                Arguments.of(
                        "Without discount",
                        CheckoutPrice.of(BigDecimal.valueOf(49.99), BigDecimal.ZERO)
                )
        );
    }

    private static Stream<Arguments> isEligibleForFreeShippingTestParams() {
        return Stream.of(
                Arguments.of("Not eligible", order1(), false),
                Arguments.of("Eligible", order2(), true)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("orderFromParams")
    public void fromTests(
            String testCase,
            CheckoutPrice checkoutPrice
    ) {
        var result = Order.from(user(), checkoutPrice, cartWithTwoItems());

        assertThat(result, not(nullValue()));
        assertThat(result, allOf(
                hasProperty("totalPrice", comparesEqualTo(checkoutPrice.getTotalPrice())),
                hasProperty("discountAmount", comparesEqualTo(checkoutPrice.getDiscountAmount())),
                hasProperty("status", equalTo(OrderStatus.CREATED))
        ));
        assertThat(result.getItems().size(), equalTo(2));
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
