package com.github.jbence1994.webshop.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wishlist")
@RequiredArgsConstructor
@Slf4j
public class WishlistController {
    private final WishlistService wishlistService;
    private final WishlistMapper wishlistMapper;

    @PostMapping
    public ResponseEntity<WishlistProductDto> addProductToWishlist(@Valid @RequestBody AddProductToWishlistRequest request) {
        var product = wishlistService.addProductToWishlist(request.productId());

        var wishlistProductDto = wishlistMapper.toDto(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(wishlistProductDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProductFromWishlist(@Valid @RequestBody DeleteProductFromWishlistRequest request) {
        wishlistService.deleteProductFromWishlist(request.productId());

        return ResponseEntity.noContent().build();
    }
}
