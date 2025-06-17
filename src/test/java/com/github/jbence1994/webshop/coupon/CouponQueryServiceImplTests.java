package com.github.jbence1994.webshop.coupon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.github.jbence1994.webshop.coupon.CouponTestObject.coupon1;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.coupon3;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CouponQueryServiceImplTests {

    @Mock
    private CouponRepository couponRepository;

    @InjectMocks
    private CouponQueryServiceImpl couponService;

    @Test
    public void getCouponsTest() {
        when(couponRepository.findAll(any(Sort.class))).thenReturn(List.of(coupon1(), coupon3()));

        var result = couponService.getCoupons();

        assertThat(result.size(), equalTo(1));

        verify(couponRepository, times(1)).findAll(any(Sort.class));
    }

    @Test
    public void getCouponsByUserTest() {
        when(couponRepository.getCouponsByUser(any())).thenReturn(List.of(coupon1(), coupon3()));

        var result = couponService.getCouponsByUser(any());

        assertThat(result.size(), equalTo(1));

        verify(couponRepository, times(1)).getCouponsByUser(any());
    }
}
