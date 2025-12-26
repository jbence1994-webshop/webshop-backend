package com.github.jbence1994.webshop.coupon;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CouponQueryServiceImpl implements CouponQueryService {
    private final CouponRepository couponRepository;

    @Override
    public List<Coupon> getCoupons() {
        return couponRepository.findAll().stream()
                .filter(coupon -> !coupon.isExpired())
                .toList();
    }

    @Override
    public Coupon getCoupon(String code) {
        return couponRepository.findById(code)
                .orElseThrow(() -> new CouponNotFoundException(code));
    }

    @Override
    public boolean hasUserRedeemedCoupon(Long userId, String code) {
        return couponRepository.existsUserRedeemedCoupon(userId, code) == 1;
    }
}
