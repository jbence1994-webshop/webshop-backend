package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.cart.Cart;
import com.github.jbence1994.webshop.coupon.Coupon;
import com.github.jbence1994.webshop.order.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithOneItem;
import static com.github.jbence1994.webshop.cart.CartTestObject.emptyCart;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_SESSION_ID;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_URL;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CREATED_AT;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.EXPIRED_CHECKOUT_SESSION_EXPIRATION_DATE;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.NOT_EXPIRED_CHECKOUT_SESSION_EXPIRATION_DATE;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.percentOffNotExpiredCoupon;
import static com.github.jbence1994.webshop.order.OrderTestObject.createdOrder1;

public final class CheckoutSessionTestObject {
    public static CheckoutSession checkoutSession() {
        return buildCheckoutSession(
                cartWithOneItem(),
                BigDecimal.valueOf(49.99),
                BigDecimal.valueOf(49.99),
                BigDecimal.ZERO,
                null,
                CheckoutStatus.PENDING,
                NOT_EXPIRED_CHECKOUT_SESSION_EXPIRATION_DATE,
                null,
                null
        );
    }

    public static CheckoutSession checkoutSessionWithOrderAndCheckoutUrl() {
        return buildCheckoutSession(
                cartWithOneItem(),
                BigDecimal.valueOf(49.99),
                BigDecimal.valueOf(49.99),
                BigDecimal.ZERO,
                null,
                CheckoutStatus.PENDING,
                NOT_EXPIRED_CHECKOUT_SESSION_EXPIRATION_DATE,
                createdOrder1(),
                CHECKOUT_URL
        );
    }

    public static CheckoutSession checkoutSessionWithPercentOffTypeOfAppliedCoupon() {
        return buildCheckoutSession(
                cartWithOneItem(),
                BigDecimal.valueOf(49.99),
                BigDecimal.valueOf(5.00),
                BigDecimal.valueOf(44.99),
                percentOffNotExpiredCoupon(),
                CheckoutStatus.PENDING,
                NOT_EXPIRED_CHECKOUT_SESSION_EXPIRATION_DATE,
                null,
                null
        );
    }

    public static CheckoutSession expiredCheckoutSessionWithPercentOffTypeOfAppliedCoupon() {
        return buildCheckoutSession(
                cartWithOneItem(),
                BigDecimal.valueOf(49.99),
                BigDecimal.valueOf(5.00),
                BigDecimal.valueOf(44.99),
                percentOffNotExpiredCoupon(),
                CheckoutStatus.PENDING,
                EXPIRED_CHECKOUT_SESSION_EXPIRATION_DATE,
                null,
                null
        );
    }

    public static CheckoutSession checkoutSessionWithEmptyCart() {
        return buildCheckoutSession(
                emptyCart(),
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                null,
                CheckoutStatus.PENDING,
                NOT_EXPIRED_CHECKOUT_SESSION_EXPIRATION_DATE,
                null,
                null
        );
    }

    public static CheckoutSession canceledCheckoutSession() {
        return buildCheckoutSession(
                cartWithOneItem(),
                BigDecimal.valueOf(49.99),
                BigDecimal.valueOf(49.99),
                BigDecimal.ZERO,
                null,
                CheckoutStatus.CANCELED,
                NOT_EXPIRED_CHECKOUT_SESSION_EXPIRATION_DATE,
                null,
                null
        );
    }

    public static CheckoutSession completedCheckoutSession() {
        return buildCheckoutSession(
                cartWithOneItem(),
                BigDecimal.valueOf(49.99),
                BigDecimal.valueOf(49.99),
                BigDecimal.ZERO,
                null,
                CheckoutStatus.COMPLETED,
                NOT_EXPIRED_CHECKOUT_SESSION_EXPIRATION_DATE,
                createdOrder1(),
                CHECKOUT_URL
        );
    }

    public static CheckoutSession failedCheckoutSession() {
        return buildCheckoutSession(
                cartWithOneItem(),
                BigDecimal.valueOf(49.99),
                BigDecimal.valueOf(49.99),
                BigDecimal.ZERO,
                null,
                CheckoutStatus.FAILED,
                NOT_EXPIRED_CHECKOUT_SESSION_EXPIRATION_DATE,
                createdOrder1(),
                CHECKOUT_URL
        );
    }

    public static CheckoutSession expiredCheckoutSession() {
        return buildCheckoutSession(
                cartWithOneItem(),
                BigDecimal.valueOf(49.99),
                BigDecimal.valueOf(49.99),
                BigDecimal.ZERO,
                null,
                CheckoutStatus.EXPIRED,
                EXPIRED_CHECKOUT_SESSION_EXPIRATION_DATE,
                null,
                null
        );
    }

    private static CheckoutSession buildCheckoutSession(
            Cart cart,
            BigDecimal originalCartTotal,
            BigDecimal cartTotal,
            BigDecimal discountAmount,
            Coupon appliedCoupon,
            CheckoutStatus status,
            LocalDateTime expirationDate,
            Order order,
            String checkoutUrl
    ) {
        return new CheckoutSession(
                CHECKOUT_SESSION_ID,
                cart,
                originalCartTotal,
                cartTotal,
                discountAmount,
                appliedCoupon,
                status,
                CREATED_AT,
                expirationDate,
                order,
                checkoutUrl
        );
    }
}
