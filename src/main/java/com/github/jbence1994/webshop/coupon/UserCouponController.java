package com.github.jbence1994.webshop.coupon;

import com.github.jbence1994.webshop.user.UserQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/{id}/coupons")
@AllArgsConstructor
public class UserCouponController {
    private final UserQueryService userQueryService;
    private final CouponQueryService couponQueryService;
    private final CouponMapper couponMapper;

    @GetMapping
    public List<CouponDto> getCoupons(@PathVariable("id") Long id) {
        // FIXME: Refactor later to fetch all coupons by the current, logged in User
        var user = userQueryService.getUser(id);

        return couponQueryService.getCouponsByUser(user).stream()
                .map(couponMapper::toDto)
                .toList();
    }
}
