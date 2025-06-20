package com.github.jbence1994.webshop.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, String> {
    @Query("SELECT c FROM Coupon c JOIN c.users u WHERE u.id = :userId AND c.expirationDate > CURRENT_TIMESTAMP ORDER BY c.expirationDate")
    List<Coupon> findAllByUser(@Param("userId") Long userId);
}
