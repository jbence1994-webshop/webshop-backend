package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.user.UserService;
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
    private final UserService userService;

    @Override
    public void createProduct(Product product) {
        save(product);
    }

    @Override
    public void updateProduct(Product product) {
        save(product);
    }

    @Override
    public Product addProductToWishlist(Long productId) {
        try {
            var user = authService.getCurrentUser();
            var product = productQueryService.getProduct(productId);

            user.addFavoriteProduct(product);

            userService.updateUser(user);

            return product;
        } catch (Exception exception) {
            throw new ProductAlreadyOnWishlistException(productId);
        }
    }

    @Override
    public void deleteProductFromWishlist(Long productId) {
        var user = authService.getCurrentUser();

        user.removeFavoriteProduct(productId);

        userService.updateUser(user);
    }

    @Override
    public Product createProductRating(Long id, Byte ratingValue) {
        try {
            validate(ratingValue);

            var product = productQueryService.getProduct(id);
            var user = authService.getCurrentUser();

            product.addRating(new ProductRating(product, user, ratingValue));

            productRepository.save(product);

            return product;
        } catch (InvalidProductRatingValueException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new ProductAlreadyRatedException(id);
        }
    }

    @Override
    public Product updateProductRating(Long id, Byte ratingValue) {
        validate(ratingValue);

        var product = productQueryService.getProduct(id);
        var user = authService.getCurrentUser();

        product.updateRating(user.getId(), ratingValue);

        productRepository.save(product);

        return product;
    }

    @Override
    public Product createProductReview(Long id, String review) {
        var product = productQueryService.getProduct(id);
        var user = authService.getCurrentUser();

        product.addReview(new ProductReview(product, user, review));

        productRepository.save(product);

        return product;
    }

    @Override
    public ProductReviewSummary generateProductReviewSummary(Long id) {
        var product = productQueryService.getProduct(id);

        var productReviewSummary = productReviewSummaryQueryService
                .getProductReviewSummary(product.getId())
                .orElseGet(() -> createSummary(product));

        if (productReviewSummary.isExpired()) {
            updateSummary(product, productReviewSummary);
        }

        return productReviewSummary;
    }

    private void save(Product product) {
        productRepository.save(product);
    }

    private void validate(int ratingValue) {
        if (ratingValue < 1 || ratingValue > 5) {
            throw new InvalidProductRatingValueException();
        }
    }

    private ProductReviewSummary createSummary(Product product) {
        var productReviewSummaryText = productReviewSummarizer.summarizeProductReviews(product.getId());

        var productReviewSummary = new ProductReviewSummary(product, productReviewSummaryText);

        productReviewSummaryService.createProductReviewSummary(productReviewSummary);

        return productReviewSummary;
    }

    private void updateSummary(Product product, ProductReviewSummary productReviewSummary) {
        var productReviewSummaryText = productReviewSummarizer.summarizeProductReviews(product.getId());

        productReviewSummary.setText(productReviewSummaryText);

        productReviewSummaryService.updateProductReviewSummary(productReviewSummary);
    }
}
