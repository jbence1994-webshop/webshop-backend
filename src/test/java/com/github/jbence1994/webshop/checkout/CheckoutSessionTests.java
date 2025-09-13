package com.github.jbence1994.webshop.checkout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSession;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSessionWithFixedAmountTypeOfAppliedCoupon;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSessionWithFreeShippingTypeOfAppliedCoupon;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSessionWithPercentOffTypeOfAppliedCoupon;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CheckoutSessionTests {

    private static Stream<Arguments> checkoutSessionHasCouponAppliedParams() {
        return Stream.of(
                Arguments.of("Checkout session has coupon applied", checkoutSessionWithPercentOffTypeOfAppliedCoupon(), true),
                Arguments.of("Checkout session does not have a coupon applied", checkoutSession(), false)
        );
    }

    private static Stream<Arguments> checkoutSessionCheckoutTotalParams() {
        return Stream.of(
                Arguments.of(
                        "Without applied coupon",
                        checkoutSession(),
                        CheckoutPrice.withShippingCost(BigDecimal.valueOf(49.99), BigDecimal.ZERO)
                ),
                Arguments.of(
                        "With fixed amount type of applied coupon",
                        checkoutSessionWithFixedAmountTypeOfAppliedCoupon(),
                        CheckoutPrice.withShippingCost(BigDecimal.valueOf(34.99), BigDecimal.valueOf(15.00))
                ),
                Arguments.of(
                        "With percent off type of applied coupon",
                        checkoutSessionWithPercentOffTypeOfAppliedCoupon(),
                        CheckoutPrice.withShippingCost(BigDecimal.valueOf(44.99), BigDecimal.valueOf(5.00))
                ),
                Arguments.of(
                        "With free shipping type of applied coupon",
                        checkoutSessionWithFreeShippingTypeOfAppliedCoupon(),
                        CheckoutPrice.withFreeShipping(BigDecimal.valueOf(49.99))
                )
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

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("checkoutSessionCheckoutTotalParams")
    public void calculateCheckoutTotalTests(
            String testCase,
            CheckoutSession checkoutSession,
            CheckoutPrice expectedResult
    ) {
        var result = checkoutSession.calculateCheckoutTotal();

        assertThat(result.getTotalPrice(), comparesEqualTo(expectedResult.getTotalPrice()));
        assertThat(result.getDiscountAmount(), comparesEqualTo(expectedResult.getDiscountAmount()));
        assertThat(result.getShippingCost(), comparesEqualTo(expectedResult.getShippingCost()));
    }
}
