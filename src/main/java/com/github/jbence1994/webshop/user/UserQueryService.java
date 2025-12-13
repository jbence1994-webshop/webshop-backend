package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.product.Product;

import java.util.List;

public interface UserQueryService {
    EncryptedUser getEncryptedUser(Long id);

    DecryptedUser getDecryptedUser(Long id);

    EncryptedUser getEncryptedUser(String email);

    DecryptedUser getDecryptedUser(String email);

    List<Product> getWishlist();
}
