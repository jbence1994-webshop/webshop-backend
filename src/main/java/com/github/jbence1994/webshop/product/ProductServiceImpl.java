package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
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
    public ProductRatingResponse createProductRating(Long id, Byte rateValue) {
        validate(rateValue);

        var product = productQueryService.getProduct(id);
        var user = authService.getCurrentUser();

        var productRating = new ProductRating(product, user.getProfile(), rateValue);

        product.addRating(productRating);

        productRepository.save(product);

        return new ProductRatingResponse(id, rateValue, product.calculateAverageRating(), product.getRatings().size());
    }

    @Override
    public ProductRatingResponse updateProductRating(Long id, Byte rateValue) {
        validate(rateValue);

        var product = productQueryService.getProduct(id);
        var user = authService.getCurrentUser();

        product.updateRating(user.getId(), rateValue);

        productRepository.save(product);

        return new ProductRatingResponse(id, rateValue, product.calculateAverageRating(), product.getRatings().size());
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
