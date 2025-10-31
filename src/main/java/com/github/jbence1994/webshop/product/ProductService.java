package com.github.jbence1994.webshop.product;

public interface ProductService {
    void createProduct(Product product);

    void updateProduct(Product product);

    Product addProductToWishlist(Long productId);

    void deleteProductFromWishlist(Long productId);

    Product createProductRating(Long id, Byte ratingValue);

    Product updateProductRating(Long id, Byte ratingValue);

    Product createProductReview(Long id, String review);

    ProductReviewSummary generateProductReviewSummary(Long id);
}
