package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.product.Product;

public interface UserService {
    void registerUser(User user);

    void changePassword(String oldPassword, String newPassword);

    void forgotPassword(String email);

    void resetPassword(String temporaryPassword, String newPassword);

    void updateUser(User user);

    void deleteUser(Long id);

    Product addProductToWishlist(Long productId);

    void deleteProductFromWishlist(Long productId);
}
