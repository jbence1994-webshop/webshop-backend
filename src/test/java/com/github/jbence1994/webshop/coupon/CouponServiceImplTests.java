package com.github.jbence1994.webshop.coupon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CouponServiceImplTests {

    @Mock
    private CouponRepository couponRepository;

    @InjectMocks
    private CouponServiceImpl couponService;

    @Test
    public void getCouponsTest() {
        doNothing().when(couponRepository).redeemCoupon(any(), any(), any());

        assertDoesNotThrow(() -> couponService.redeemCoupon(1L, COUPON_1_CODE, 1L));

        verify(couponRepository, times(1)).redeemCoupon(any(), any(), any());
    }
}
