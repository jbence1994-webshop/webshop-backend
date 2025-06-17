package com.github.jbence1994.webshop.coupon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.github.jbence1994.webshop.coupon.CouponDtoTestObject.couponDto1;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.coupon1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CouponControllerTests {

    @Mock
    private CouponQueryService couponQueryService;

    @Mock
    private CouponMapper couponMapper;

    @InjectMocks
    private CouponController couponController;

    @Test
    public void getCouponsTest() {
        when(couponQueryService.getCoupons()).thenReturn(List.of(coupon1()));
        when(couponMapper.toDto(any())).thenReturn(couponDto1());

        var result = couponController.getCoupons();

        assertThat(result.size(), equalTo(1));

        verify(couponQueryService, times(1)).getCoupons();
        verify(couponMapper, times(1)).toDto(any());
    }
}
