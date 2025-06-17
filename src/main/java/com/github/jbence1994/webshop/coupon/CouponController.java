package com.github.jbence1994.webshop.coupon;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coupons")
@AllArgsConstructor
public class CouponController {
    private final CouponQueryService couponQueryService;
    private final CouponMapper couponMapper;

    @GetMapping
    public List<CouponDto> getCoupons() {
        return couponQueryService.getCoupons().stream()
                .map(couponMapper::toDto)
                .toList();
    }

    @GetMapping("/{code}")
    public CouponDto getCoupon(@PathVariable String code) {
        var coupon = couponQueryService.getCoupon(code);
        return couponMapper.toDto(coupon);
    }
}
