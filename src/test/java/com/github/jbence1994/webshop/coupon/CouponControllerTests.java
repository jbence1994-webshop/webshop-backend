package com.github.jbence1994.webshop.coupon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static com.github.jbence1994.webshop.coupon.CouponDtoTestObject.couponDto1;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.percentOffNotExpiredCoupon;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CouponControllerTests {

    @Mock
    private CouponQueryService couponQueryService;

    @Mock
    private CouponService couponService;

    @Mock
    private CouponMapper couponMapper;

    @InjectMocks
    private CouponController couponController;

    @Test
    public void getCouponsTest() {
        when(couponQueryService.getCoupons()).thenReturn(List.of(percentOffNotExpiredCoupon()));
        when(couponMapper.toDto(any())).thenReturn(couponDto1());

        var result = couponController.getCoupons();

        assertThat(result.size(), equalTo(1));

        verify(couponQueryService, times(1)).getCoupons();
        verify(couponMapper, times(1)).toDto(any());
    }

    @Test
    public void createCouponTest() {
        when(couponMapper.toEntity(any())).thenReturn(percentOffNotExpiredCoupon());
        doNothing().when(couponService).createCoupon(any());

        var result = couponController.createCoupon(couponDto1());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().code(), equalTo(percentOffNotExpiredCoupon().getCode()));
        assertThat(result.getBody().type(), equalTo(percentOffNotExpiredCoupon().getType()));
        assertThat(result.getBody().value(), equalTo(percentOffNotExpiredCoupon().getValue()));
        assertThat(result.getBody().description(), equalTo(percentOffNotExpiredCoupon().getDescription()));
        assertThat(result.getBody().expirationDate(), equalTo(percentOffNotExpiredCoupon().getExpirationDate()));

        verify(couponMapper, times(1)).toEntity(any());
        verify(couponService, times(1)).createCoupon(any());
    }
}
