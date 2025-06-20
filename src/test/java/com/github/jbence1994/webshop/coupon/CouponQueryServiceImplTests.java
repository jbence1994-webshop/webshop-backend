package com.github.jbence1994.webshop.coupon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.coupon1;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.coupon3;
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

    @InjectMocks
    private CouponQueryServiceImpl couponQueryService;

    @Test
    public void getCouponsTest() {
        when(couponRepository.findAll(any(Sort.class))).thenReturn(List.of(coupon1(), coupon3()));

        var result = couponQueryService.getCoupons();

        assertThat(result.size(), equalTo(1));

        verify(couponRepository, times(1)).findAll(any(Sort.class));
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

    @Test
    public void getCouponsByUserTest() {
        when(couponRepository.getCouponsByUser(any())).thenReturn(List.of(coupon1(), coupon3()));

        var result = couponQueryService.getCouponsByUser(any());

        assertThat(result.size(), equalTo(1));

        verify(couponRepository, times(1)).getCouponsByUser(any());
    }
}
