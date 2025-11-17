package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.product.Product;

import java.util.List;

public interface UserQueryService {
    User getUser(Long id);

    User getUser(String email);

    List<Product> getWishlist(Long id);
}
