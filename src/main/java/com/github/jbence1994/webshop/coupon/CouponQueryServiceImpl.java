package com.github.jbence1994.webshop.coupon;

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
    public List<Coupon> getCouponsByUser(Long userId) {
        var userCoupons = couponRepository.findByUserId(userId);

        return userCoupons.stream()
                .filter(coupon -> !coupon.isExpired())
                .toList();
    }
}
