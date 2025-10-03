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
    public Product createProductRating(Long id, Byte value) {
        validate(value);

        var product = productQueryService.getProduct(id);
        var user = authService.getCurrentUser();

        var productRating = new ProductRating(product, user.getProfile(), value);

        product.addRating(productRating);

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

        var productReview = new ProductReview(product, user.getProfile(), review);

        product.addReview(productReview);

        productRepository.save(product);

        return product;
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
