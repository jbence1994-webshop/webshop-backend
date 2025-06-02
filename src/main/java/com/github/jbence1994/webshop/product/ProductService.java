package com.github.jbence1994.webshop.product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(Long id);

    boolean existsById(Long id);

    Product createProduct(Product product);

    void updateProduct(Product product);
}
