package com.github.jbence1994.webshop.checkout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSession;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSessionWithPercentOffTypeOfAppliedCoupon;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CheckoutSessionTests {

    private static Stream<Arguments> checkoutSessionHasCouponAppliedParams() {
        return Stream.of(
                Arguments.of("Checkout session has coupon applied", checkoutSessionWithPercentOffTypeOfAppliedCoupon(), true),
                Arguments.of("Checkout session does not have a coupon applied", checkoutSession(), false)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("checkoutSessionHasCouponAppliedParams")
    public void hasCouponAppliedTests(
            String testCase,
            CheckoutSession checkoutSession,
            boolean expectedResult
    ) {
        var result = checkoutSession.hasCouponApplied();

        assertThat(result, is(expectedResult));
    }

    @Test
    public void getCouponCodeTest() {
        var result = checkoutSessionWithPercentOffTypeOfAppliedCoupon().getCouponCode();

        assertThat(result, equalTo(COUPON_1_CODE));
    }
}
