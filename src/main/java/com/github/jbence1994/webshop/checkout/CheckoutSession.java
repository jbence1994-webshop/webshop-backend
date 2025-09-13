package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.cart.Cart;
import com.github.jbence1994.webshop.coupon.Coupon;
import com.github.jbence1994.webshop.coupon.DiscountType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratedColumn;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "checkout_sessions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckoutSession {

    // TODO: CheckoutPrice shall be persisted later.
    // TODO: expiration shall be persisted later.

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "applied_coupon")
    private Coupon appliedCoupon;

    @Enumerated(EnumType.STRING)
    private CheckoutStatus status;

    @Column(insertable = false, updatable = false)
    @GeneratedColumn("created_at")
    private LocalDateTime createdAt;

    public boolean hasCouponApplied() {
        return appliedCoupon != null;
    }

    public String getCouponCode() {
        return appliedCoupon.getCode();
    }

    public CheckoutPrice calculateCheckoutTotal() {
        var cartTotal = cart.calculateTotal();

        if (!hasCouponApplied()) {
            return CheckoutPrice.withShippingCost(cartTotal);
        }

        if (DiscountType.FREE_SHIPPING.equals(appliedCoupon.getType())) {
            return CheckoutPrice.withFreeShipping(cartTotal);
        }

        return CheckoutPriceAdjustmentStrategyFactory
                .getCheckoutPriceAdjustmentStrategy(appliedCoupon.getType())
                .adjustCheckoutPrice(cartTotal, appliedCoupon.getValue());
    }
}
