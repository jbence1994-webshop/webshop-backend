package com.github.jbence1994.webshop.coupon;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coupons")
@CrossOrigin
@RequiredArgsConstructor
public class CouponController {
    private final CouponQueryService couponQueryService;
    private final CouponMapper couponMapper;

    @GetMapping
    public List<CouponDto> getCoupons() {
        return couponQueryService.getCoupons().stream()
                .map(couponMapper::toDto)
                .toList();
    }
}
