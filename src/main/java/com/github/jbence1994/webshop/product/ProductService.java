package com.github.jbence1994.webshop.product;

public interface ProductService {
    void createProduct(Product product);

    void updateProduct(Product product);

    ProductRatingResponse createProductRating(Long id, Byte value);

    ProductRatingResponse updateProductRating(Long id, Byte value);

    ProductFeedbackResponse createProductFeedback(Long id, String feedback);
}
