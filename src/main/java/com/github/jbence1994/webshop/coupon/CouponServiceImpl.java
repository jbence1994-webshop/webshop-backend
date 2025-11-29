package com.github.jbence1994.webshop.coupon;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;

    @Override
    public void createCoupon(Coupon coupon) {
        couponRepository.save(coupon);
    }

    @Override
    public void redeemCoupon(Long userId, String couponCode, Long orderId) {
        couponRepository.redeemCoupon(userId, couponCode, orderId);
    }
}
