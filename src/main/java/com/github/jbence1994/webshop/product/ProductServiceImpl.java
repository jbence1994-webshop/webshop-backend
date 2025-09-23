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
    public RateProductResponse rateProduct(Long id, Byte value) {
        var product = productQueryService.getProduct(id);
        var user = authService.getCurrentUser();

        var productRating = new ProductRating(product, user.getProfile(), value);

        product.addRating(productRating);

        productRepository.save(product);

        return new RateProductResponse(id, value, product.calculateAverageRating(), product.getRatings().size());
    }

    private void save(Product product) {
        productRepository.save(product);
    }
}
