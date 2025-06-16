package com.github.jbence1994.webshop.coupon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.github.jbence1994.webshop.coupon.CouponDtoTestObject.notExpiredCouponDto;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.notExpiredCoupon;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserCouponsControllerTests {

    @Mock
    private CouponQueryService couponQueryService;

    @Mock
    private CouponMapper couponMapper;

    @InjectMocks
    private UserCouponsController userCouponsController;

    @Test
    public void getCouponsTest() {
        when(couponQueryService.getCouponsByUser(any())).thenReturn(List.of(notExpiredCoupon()));
        when(couponMapper.toDto(any())).thenReturn(notExpiredCouponDto());

        var result = userCouponsController.getCoupons(1L);

        assertThat(result.size(), equalTo(1));

        verify(couponQueryService, times(1)).getCouponsByUser(any());
        verify(couponMapper, times(1)).toDto(any());
    }
}
