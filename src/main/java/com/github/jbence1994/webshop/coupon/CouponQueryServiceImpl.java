package com.github.jbence1994.webshop.coupon;

import com.github.jbence1994.webshop.user.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CouponQueryServiceImpl implements CouponQueryService {
    private final CouponRepository couponRepository;

    @Override
    public List<Coupon> getCoupons() {
        var sortedCoupons = couponRepository
                .findAll(Sort.by(Sort.Direction.ASC, "expirationDate"));

        return sortedCoupons.stream()
                .filter(coupon -> !coupon.isExpired())
                .toList();
    }

    @Override
    public Coupon getCoupon(String code) {
        return couponRepository
                .findById(code)
                .orElseThrow(() -> new CouponNotFoundException(code));
    }

    @Override
    public List<Coupon> getCouponsByUser(User user) {
        var userCoupons = couponRepository.getCouponsByUser(user);

        return userCoupons.stream()
                .filter(coupon -> !coupon.isExpired())
                .toList();
    }
}
