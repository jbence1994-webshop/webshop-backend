package com.github.jbence1994.webshop.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CouponRepository extends JpaRepository<Coupon, String> {
    @Query(
            value = "SELECT EXISTS (SELECT * FROM user_coupons WHERE user_id = :userId AND coupon_code = :couponCode AND redeemed = 1);",
            nativeQuery = true
    )
    int isCouponRedeemed(@Param("userId") Long userId, @Param("couponCode") String couponCode);

    @Modifying
    @Query(
            value = """
                    UPDATE user_coupons
                    SET redeemed = 1, redeemed_at = CURRENT_TIMESTAMP, order_id = :orderId
                    WHERE user_id = :userId AND coupon_code = :couponCode
                    """,
            nativeQuery = true
    )
    void redeemCoupon(
            @Param("userId") Long userId,
            @Param("couponCode") String couponCode,
            @Param("orderId") Long orderId
    );
}
