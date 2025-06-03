package com.github.jbence1994.webshop.product;

import java.util.List;

public interface ProductQueryService {
    List<Product> getProducts(String sortBy);

    Product getProduct(Long id);

    boolean isProductExistById(Long id);
}
