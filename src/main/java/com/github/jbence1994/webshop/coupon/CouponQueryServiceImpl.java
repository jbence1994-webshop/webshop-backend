package com.github.jbence1994.webshop.coupon;

import com.github.jbence1994.webshop.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponQueryServiceImpl implements CouponQueryService {
    private final CouponRepository couponRepository;
    private final AuthService authService;

    @Override
    public List<Coupon> getCoupons() {
        return couponRepository.findAll().stream()
                .filter(coupon -> !coupon.isExpired())
                .toList();
    }

    @Override
    public Coupon getCoupon(String code) {
        var user = authService.getCurrentUser();

        return couponRepository
                .findByCouponCodeAndUserId(code, user.getId())
                .orElseThrow(() -> new CouponNotFoundException(code));
    }

    @Override
    public boolean isCouponRedeemed(String code) {
        var user = authService.getCurrentUser();

        return couponRepository.isCouponRedeemed(user.getId(), code) == 1;
    }
}
