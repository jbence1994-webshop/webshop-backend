package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.cart.Cart;
import com.github.jbence1994.webshop.coupon.Coupon;
import com.github.jbence1994.webshop.order.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratedColumn;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "checkout_sessions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private BigDecimal originalCartTotal;

    private BigDecimal cartTotal;

    private BigDecimal discountAmount;

    @ManyToOne
    @JoinColumn(name = "applied_coupon")
    private Coupon appliedCoupon;

    @Enumerated(EnumType.STRING)
    private CheckoutStatus status;

    @Column(insertable = false, updatable = false)
    @GeneratedColumn("created_at")
    private LocalDateTime createdAt;

    private LocalDateTime expirationDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private String checkoutUrl;

    public static CheckoutSession from(Cart cart) {
        var cartTotal = cart.calculateTotal();

        var checkoutSession = new CheckoutSession();

        checkoutSession.cart = cart;
        checkoutSession.originalCartTotal = cartTotal;
        checkoutSession.cartTotal = cartTotal;
        checkoutSession.discountAmount = BigDecimal.ZERO;
        checkoutSession.status = CheckoutStatus.PENDING;
        checkoutSession.expirationDate = LocalDateTime.now().plusMinutes(60);

        return checkoutSession;
    }

    public Optional<Coupon> getAppliedCoupon() {
        return Optional.ofNullable(appliedCoupon);
    }

    public void applyCoupon(Coupon coupon) {
        this.appliedCoupon = coupon;
        this.cartTotal = originalCartTotal;

        var adjustedCartTotal = CartTotalAdjustmentStrategyFactory
                .getStrategy(appliedCoupon.getType())
                .adjustCartTotal(cartTotal, appliedCoupon.getValue());

        this.cartTotal = adjustedCartTotal.getCartTotal();
        this.discountAmount = adjustedCartTotal.getDiscountAmount();
    }

    public void removeCoupon() {
        this.appliedCoupon = null;
        this.cartTotal = originalCartTotal;
        this.discountAmount = BigDecimal.ZERO;
    }

    public boolean isExpired() {
        return expirationDate.isBefore(LocalDateTime.now());
    }
}
