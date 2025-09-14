package com.github.jbence1994.webshop.coupon;

import com.github.jbence1994.webshop.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_2_CODE;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.percentOffNotExpiredCoupon;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CouponQueryServiceImplTests {

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private CouponQueryServiceImpl couponQueryService;

    private static Stream<Arguments> isCouponRedeemedTestParams() {
        return Stream.of(
                Arguments.of("Coupon is redeemed", COUPON_1_CODE, 1, true),
                Arguments.of("Coupon is not yet redeemed", COUPON_2_CODE, 0, false)
        );
    }

    @Test
    public void getCouponsTest() {
        when(authService.getCurrentUser()).thenReturn(user());
        when(couponRepository.findAllByUser(any())).thenReturn(List.of(percentOffNotExpiredCoupon()));

        var result = couponQueryService.getCoupons();

        assertThat(result.size(), equalTo(1));

        verify(couponRepository, times(1)).findAllByUser(any());
    }

    @Test
    public void getCouponTest_HappyPath() {
        when(authService.getCurrentUser()).thenReturn(user());
        when(couponRepository.findByCouponCodeAndUserId(any(), any())).thenReturn(Optional.of(percentOffNotExpiredCoupon()));

        var result = assertDoesNotThrow(() -> couponQueryService.getCoupon(COUPON_1_CODE));

        assertThat(result, not(nullValue()));
        assertThat(result, allOf(
                hasProperty("code", equalTo(percentOffNotExpiredCoupon().getCode())),
                hasProperty("type", equalTo(percentOffNotExpiredCoupon().getType())),
                hasProperty("value", equalTo(percentOffNotExpiredCoupon().getValue())),
                hasProperty("description", equalTo(percentOffNotExpiredCoupon().getDescription())),
                hasProperty("expirationDate", equalTo(percentOffNotExpiredCoupon().getExpirationDate()))
        ));
    }

    @Test
    public void getCouponTest_UnhappyPath_CouponNotFoundException() {
        when(authService.getCurrentUser()).thenReturn(user());
        when(couponRepository.findByCouponCodeAndUserId(any(), any())).thenReturn(Optional.empty());

        var result = assertThrows(
                CouponNotFoundException.class,
                () -> couponQueryService.getCoupon(COUPON_2_CODE)
        );

        assertThat(result.getMessage(), equalTo("No coupon was found with the given coupon code: 'SPRING15'."));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("isCouponRedeemedTestParams")
    public void isCouponRedeemedTests(
            String testCase,
            String couponCode,
            int returnValueFromRepository,
            boolean expectedResult
    ) {
        when(couponRepository.isCouponRedeemed(any())).thenReturn(returnValueFromRepository);

        var result = couponQueryService.isCouponRedeemed(couponCode);

        assertThat(result, equalTo(expectedResult));
    }
}
