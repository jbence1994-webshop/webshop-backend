package com.github.jbence1994.webshop.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public void createProduct(Product product) {
        save(product);
    }

    @Override
    public void updateProduct(Product product) {
        save(product);
    }

    private void save(Product product) {
        productRepository.save(product);
    }
}
