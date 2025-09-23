package com.github.jbence1994.webshop.product;

public interface ProductService {
    void createProduct(Product product);

    void updateProduct(Product product);

    ProductRatingResponse createProductRating(Long id, Byte rateValue);

    ProductRatingResponse updateProductRating(Long id, Byte rateValue);
}
