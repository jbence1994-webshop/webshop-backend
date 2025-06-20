package com.github.jbence1994.webshop.coupon;

import com.github.jbence1994.webshop.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.coupon1;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.coupon2;
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

    @Test
    public void getCouponsTest() {
        when(authService.getCurrentUser()).thenReturn(user());
        when(couponRepository.findAllByUser(any())).thenReturn(List.of(coupon1(), coupon2()));

        var result = couponQueryService.getCoupons();

        assertThat(result.size(), equalTo(2));

        verify(couponRepository, times(1)).findAllByUser(any());
    }

    @Test
    public void getCouponTest_HappyPath() {
        when(couponRepository.findById(any())).thenReturn(Optional.of(coupon1()));

        var result = assertDoesNotThrow(() -> couponQueryService.getCoupon(COUPON_1_CODE));

        assertThat(result, not(nullValue()));
        assertThat(result, allOf(
                hasProperty("code", equalTo(coupon1().getCode())),
                hasProperty("type", equalTo(coupon1().getType())),
                hasProperty("value", equalTo(coupon1().getValue())),
                hasProperty("description", equalTo(coupon1().getDescription())),
                hasProperty("expirationDate", equalTo(coupon1().getExpirationDate()))
        ));
    }

    @Test
    public void getCouponTest_UnhappyPath_CouponNotFoundException() {
        when(couponRepository.findById(any())).thenReturn(Optional.empty());

        var result = assertThrows(
                CouponNotFoundException.class,
                () -> couponQueryService.getCoupon(COUPON_1_CODE)
        );

        assertThat(result.getMessage(), equalTo("No coupon was found with the given coupon code: 'WELCOME10'."));
    }
}
