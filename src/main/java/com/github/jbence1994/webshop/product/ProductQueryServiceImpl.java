package com.github.jbence1994.webshop.product;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getProducts(String sortBy) {
        if (sortBy == null || !Set.of("id", "price").contains(sortBy)) {
            sortBy = "id";
        }

        return productRepository.findAll(Sort.by(sortBy));
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public boolean isProductExistById(Long id) {
        return productRepository.existsById(id);
    }
}
