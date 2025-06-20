package com.github.jbence1994.webshop.coupon;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;

    @Override
    public void redeemCoupon(Long userId, String couponCode) {
        couponRepository.redeemCoupon(userId, couponCode);
    }
}
