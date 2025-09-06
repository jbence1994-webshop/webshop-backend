package com.github.jbence1994.webshop.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, String> {
    @Query("SELECT c FROM Coupon c JOIN c.users u WHERE u.id = :userId AND c.expirationDate > CURRENT_TIMESTAMP ORDER BY c.expirationDate")
    List<Coupon> findAllByUser(@Param("userId") Long userId);

    @Query("SELECT c FROM Coupon c JOIN c.users u WHERE c.code = :couponCode AND u.id = :userId")
    Optional<Coupon> findByCouponCodeAndUserId(@Param("couponCode") String couponCode, @Param("userId") Long userId);

    @Query(
            value = "SELECT EXISTS (SELECT * FROM user_coupons WHERE coupon_code = :couponCode AND redeemed = 1);",
            nativeQuery = true
    )
    int isRedeemedCoupon(@Param("couponCode") String couponCode);

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
