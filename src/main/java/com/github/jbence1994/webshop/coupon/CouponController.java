package com.github.jbence1994.webshop.coupon;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coupons")
@AllArgsConstructor
public class CouponController {
    private final CouponService couponService;
    private final CouponMapper couponMapper;

    @GetMapping
    public List<CouponDto> getCoupons() {
        return couponService.getCoupons().stream()
                .map(couponMapper::toDto)
                .toList();
    }
}
