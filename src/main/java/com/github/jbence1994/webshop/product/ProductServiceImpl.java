package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductReviewSummaryQueryService productReviewSummaryQueryService;
    private final ProductReviewSummaryService productReviewSummaryService;
    private final ProductReviewSummarizer productReviewSummarizer;
    private final ProductQueryService productQueryService;
    private final ProductRepository productRepository;
    private final AuthService authService;

    @Override
    public void createProduct(Product product) {
        save(product);
    }

    @Override
    public void updateProduct(Product product) {
        save(product);
    }

    @Override
    public Product createProductRating(Long id, Byte value) {
        validate(value);

        var product = productQueryService.getProduct(id);
        var user = authService.getCurrentUser();

        product.addRating(ProductRating.of(product, user.getProfile(), value));

        productRepository.save(product);

        return product;
    }

    @Override
    public Product updateProductRating(Long id, Byte value) {
        validate(value);

        var product = productQueryService.getProduct(id);
        var user = authService.getCurrentUser();

        product.updateRating(user.getId(), value);

        productRepository.save(product);

        return product;
    }

    @Override
    public Product createProductReview(Long id, String review) {
        var product = productQueryService.getProduct(id);
        var user = authService.getCurrentUser();

        product.addReview(ProductReview.of(product, user.getProfile(), review));

        productRepository.save(product);

        return product;
    }

    @Override
    public ProductReviewSummary generateProductReviewSummary(Long id) {
        var product = productQueryService.getProduct(id);

        var productReviewSummary = productReviewSummaryQueryService.getProductReviewSummary(product.getId());

        if (productReviewSummary == null) {
            var productReviewSummaryText = productReviewSummarizer.summarizeProductReviews(product.getId());

            productReviewSummary = ProductReviewSummary.of(product, productReviewSummaryText);

            productReviewSummaryService.createProductReviewSummary(productReviewSummary);
        }

        if (productReviewSummary.isExpired()) {
            var productReviewSummaryText = productReviewSummarizer.summarizeProductReviews(product.getId());

            productReviewSummary.setText(productReviewSummaryText);

            productReviewSummaryService.updateProductReviewSummary(productReviewSummary);
        }

        return productReviewSummary;
    }

    private void save(Product product) {
        productRepository.save(product);
    }

    private void validate(int rateValue) {
        if (rateValue < 1 || rateValue > 5) {
            throw new InvalidProductRateValueException();
        }
    }
}
