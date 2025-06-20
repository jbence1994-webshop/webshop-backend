package com.github.jbence1994.webshop.coupon;

import com.github.jbence1994.webshop.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CouponQueryServiceImpl implements CouponQueryService {
    private final CouponRepository couponRepository;
    private final AuthService authService;

    @Override
    public List<Coupon> getCoupons() {
        var user = authService.getCurrentUser();

        return couponRepository.findAllByUser(user.getId());
    }

    @Override
    public Coupon getCoupon(String code) {
        // FIXME: Refactor later to a User wouldn't be able to apply another User's coupon to it's own cart.
        return couponRepository
                .findById(code)
                .orElseThrow(() -> new CouponNotFoundException(code));
    }
}
