package com.github.jbence1994.webshop.product;

import java.util.List;

public interface ProductQueryService {
    List<Product> getProducts(
            String sortBy,
            String orderBy,
            int page,
            int size
    );

    Product getProduct(Long id);
}
