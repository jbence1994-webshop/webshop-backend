package com.github.jbence1994.webshop.cart;

import com.github.jbence1994.webshop.coupon.Coupon;
import com.github.jbence1994.webshop.product.ProductQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartQueryService cartQueryService;
    private final ProductQueryService productQueryService;
    private final CartRepository cartRepository;

    @Override
    public Cart createCart() {
        var cart = new Cart();
        cartRepository.save(cart);

        return cart;
    }

    @Override
    public CartItem addProductToCart(UUID cartId, Long productId) {
        var cart = cartQueryService.getCart(cartId);
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
    public Cart applyCouponToCart(UUID cartId, Coupon coupon) {
        var cart = cartQueryService.getCart(cartId);

        cart.applyCoupon(coupon);
        cartRepository.save(cart);

        return cart;
    }
}
