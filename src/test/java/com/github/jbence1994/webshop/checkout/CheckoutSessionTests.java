package com.github.jbence1994.webshop.checkout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithOneItem;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSession1;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSessionWithPercentOffTypeOfAppliedCoupon;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class CheckoutSessionTests {

    private static Stream<Arguments> checkoutSessionGetAppliedCouponParams() {
        return Stream.of(
                Arguments.of("Checkout session has coupon applied", checkoutSessionWithPercentOffTypeOfAppliedCoupon(), true, false),
                Arguments.of("Checkout session does not have a coupon applied", checkoutSession1(), false, true)
        );
    }

    @Test
    public void fromTest() {
        var result = CheckoutSession.from(cartWithOneItem());

        assertThat(result, not(nullValue()));
        assertThat(result, allOf(
                hasProperty("originalCartTotal", comparesEqualTo(checkoutSession1().getOriginalCartTotal())),
                hasProperty("cartTotal", comparesEqualTo(checkoutSession1().getCartTotal())),
                hasProperty("discountAmount", comparesEqualTo(checkoutSession1().getDiscountAmount())),
                hasProperty("status", equalTo(CheckoutStatus.PENDING))
        ));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("checkoutSessionGetAppliedCouponParams")
    public void getAppliedCouponTests(
            String testCase,
            CheckoutSession checkoutSession,
            boolean isPresent,
            boolean isEmpty
    ) {
        var result = checkoutSession.getAppliedCoupon();

        assertThat(result.isPresent(), is(isPresent));
        assertThat(result.isEmpty(), is(isEmpty));
    }
}
