package com.github.jbence1994.webshop.order;

import com.github.jbence1994.webshop.checkout.CheckoutSession;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithTwoItems;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSession;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSessionWithPercentOffTypeOfAppliedCoupon;
import static com.github.jbence1994.webshop.order.OrderTestConstants.SHIPPING_COST;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class OrderTests {

    private static Stream<Arguments> orderFromParams() {
        return Stream.of(
                Arguments.of(
                        "With discount",
                        checkoutSessionWithPercentOffTypeOfAppliedCoupon(),
                        BigDecimal.valueOf(44.99),
                        BigDecimal.valueOf(5.00)
                ),
                Arguments.of(
                        "Without discount",
                        checkoutSession(),
                        BigDecimal.valueOf(49.99),
                        BigDecimal.ZERO
                )
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("orderFromParams")
    public void fromTests(
            String testCase,
            CheckoutSession checkoutSession,
            BigDecimal expectedCheckoutTotal,
            BigDecimal expectedDiscountAmount
    ) {
        var result = Order.from(user(), checkoutSession, cartWithTwoItems());

        assertThat(result, not(nullValue()));
        assertThat(result, allOf(
                hasProperty("totalPrice", comparesEqualTo(expectedCheckoutTotal)),
                hasProperty("discountAmount", comparesEqualTo(expectedDiscountAmount)),
                hasProperty("shippingCost", comparesEqualTo(SHIPPING_COST)),
                hasProperty("status", equalTo(OrderStatus.CREATED))
        ));
        assertThat(result.getItems().size(), equalTo(2));
    }
}
