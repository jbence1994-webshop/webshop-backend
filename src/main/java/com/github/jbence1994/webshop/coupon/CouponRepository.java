package com.github.jbence1994.webshop.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CouponRepository extends JpaRepository<Coupon, String> {

    @Query(
            value = "SELECT EXISTS (SELECT * FROM user_coupon_redemptions WHERE user_id = :userId AND coupon_code = :couponCode);",
            nativeQuery = true
    )
    int existsUserRedeemedCoupon(
            @Param("userId") Long userId,
            @Param("couponCode") String couponCode
    );

    @Modifying
    @Query(
            value = "INSERT INTO user_coupon_redemptions (user_id, coupon_code, order_id) VALUES (:userId, :couponCode, :orderId)",
            nativeQuery = true
    )
    void redeemCoupon(
            @Param("userId") Long userId,
            @Param("couponCode") String couponCode,
            @Param("orderId") Long orderId
    );
}
