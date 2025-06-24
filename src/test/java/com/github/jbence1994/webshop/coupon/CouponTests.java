package com.github.jbence1994.webshop.coupon;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.coupon.CouponTestObject.fixedAmountExpiredCoupon;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.percentOffNotExpiredCoupon;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CouponTests {

    private static Stream<Arguments> couponParams() {
        return Stream.of(
                Arguments.of("Coupon is not expired", percentOffNotExpiredCoupon(), false),
                Arguments.of("Coupon is expired", fixedAmountExpiredCoupon(), true)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("couponParams")
    public void isExpiredTests(
            String testCase,
            Coupon coupon,
            boolean expectedResult
    ) {
        var result = coupon.isExpired();

        assertThat(result, is(expectedResult));
    }
}
