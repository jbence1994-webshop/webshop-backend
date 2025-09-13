package com.github.jbence1994.webshop.cart;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartQueryService cartQueryService;
    private final CartService cartService;
    private final CartMapper cartMapper;

    @PostMapping
    public ResponseEntity<CartDto> createCart() {
        var cart = cartService.createCart();

        var cartDto = cartMapper.toDto(cart);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartDto);
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<CartItemDto> addItemToCart(
            @PathVariable UUID id,
            @Valid @RequestBody AddItemToCartRequest request
    ) {
        var cartItem = cartService.addItemToCart(id, request.getProductId());

        var cartItemDto = cartMapper.toDto(cartItem);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @GetMapping("/{id}")
    public CartDto getCart(@PathVariable UUID id) {
        var cart = cartQueryService.getCart(id);

        return cartMapper.toDto(cart);
    }

    @PutMapping("/{cartId}/items/{productId}")
    public CartItemDto updateCartItem(
            @PathVariable UUID cartId,
            @PathVariable Long productId,
            @Valid @RequestBody UpdateCartItemRequest request
    ) {
        var cartItem = cartService.updateCartItem(
                cartId,
                productId,
                request.getQuantity()
        );

        return cartMapper.toDto(cartItem);
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<Void> deleteCartItem(
            @PathVariable UUID cartId,
            @PathVariable Long productId
    ) {
        cartService.deleteCartItem(cartId, productId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/items")
    public ResponseEntity<Void> clearCart(@PathVariable UUID id) {
        cartService.clearCart(id);

        return ResponseEntity.noContent().build();
    }
}
