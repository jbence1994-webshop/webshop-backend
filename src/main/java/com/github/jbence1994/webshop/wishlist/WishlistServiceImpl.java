package com.github.jbence1994.webshop.wishlist;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.product.Product;
import com.github.jbence1994.webshop.product.ProductQueryService;
import com.github.jbence1994.webshop.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
    private final ProductQueryService productQueryService;
    private final UserService userService;
    private final AuthService authService;

    @Override
    public Product addProductToWishlist(Long productId) {
        var user = authService.getCurrentUser();
        var product = productQueryService.getProduct(productId);

        user.addFavoriteProduct(product);

        userService.updateUser(user);

        return product;
    }

    @Override
    public void deleteProductFromWishlist(Long productId) {
        var user = authService.getCurrentUser();

        user.removeFavoriteProduct(productId);

        userService.updateUser(user);
    }
}
