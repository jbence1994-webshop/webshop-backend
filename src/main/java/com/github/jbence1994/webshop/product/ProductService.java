package com.github.jbence1994.webshop.product;

public interface ProductService {
    void createProduct(Product product);

    void updateProduct(Product product);

    Product createProductRating(Long id, Byte value);

    Product updateProductRating(Long id, Byte value);

    Product createProductReview(Long id, String review);
}
