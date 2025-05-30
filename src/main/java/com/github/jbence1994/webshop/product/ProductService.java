package com.github.jbence1994.webshop.product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(Long id);

    Product createProduct(Product product);
}
