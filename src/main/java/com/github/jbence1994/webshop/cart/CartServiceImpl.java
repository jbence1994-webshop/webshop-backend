package com.github.jbence1994.webshop.cart;

import com.github.jbence1994.webshop.coupon.CouponAlreadyRedeemedException;
import com.github.jbence1994.webshop.coupon.CouponQueryService;
import com.github.jbence1994.webshop.coupon.ExpiredCouponException;
import com.github.jbence1994.webshop.product.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartQueryService cartQueryService;
    private final ProductQueryService productQueryService;
    private final CouponQueryService couponQueryService;
    private final CartRepository cartRepository;

    @Override
    public Cart createCart() {
        var cart = new Cart();
        cartRepository.save(cart);

        return cart;
    }

    @Override
    public CartItem addProductToCart(UUID id, Long productId) {
        var cart = cartQueryService.getCart(id);
        var product = productQueryService.getProduct(productId);

        var cartItem = cart.addItem(product);
        cartRepository.save(cart);

        return cartItem;
    }

    @Override
    public CartItem updateCartItem(
            UUID cartId,
            Long productId,
            int quantity
    ) {
        var cart = cartQueryService.getCart(cartId);
        var cartItem = cartQueryService.getCartItem(cartId, productId);

        cartItem.setQuantity(quantity);
        cartRepository.save(cart);

        return cartItem;
    }

    @Override
    public void deleteCartItem(UUID cartId, Long productId) {
        var cart = cartQueryService.getCart(cartId);

        cart.removeItem(productId);
        cartRepository.save(cart);
    }

    @Override
    public void clearCart(UUID cartId) {
        var cart = cartQueryService.getCart(cartId);

        cart.clear();
        cartRepository.save(cart);
    }

    @Override
    public void deleteCart(UUID cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public Cart applyCouponToCart(UUID id, String couponCode) {
        var cart = cartQueryService.getCart(id);
        var coupon = couponQueryService.getCoupon(couponCode);

        if (cart.isEmpty()) {
            throw new EmptyCartException(id);
        }

        if (coupon.isExpired()) {
            throw new ExpiredCouponException(couponCode);
        }

        if (couponQueryService.isRedeemedCoupon(couponCode)) {
            throw new CouponAlreadyRedeemedException(couponCode);
        }

        cart.setAppliedCoupon(coupon);
        cartRepository.save(cart);

        return cart;
    }

    @Override
    public Cart removeCouponFromCart(UUID id) {
        var cart = cartQueryService.getCart(id);

        cart.setAppliedCoupon(null);
        cartRepository.save(cart);

        return cart;
    }
}
