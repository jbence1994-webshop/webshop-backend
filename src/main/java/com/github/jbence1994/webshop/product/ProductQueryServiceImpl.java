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
    public List<Product> getProducts(String sortBy, String orderBy) {
        if (sortBy == null || !Set.of("id", "price").contains(sortBy)) {
            sortBy = "id";
        }

        if (orderBy == null || !Set.of("asc", "desc").contains(orderBy)) {
            orderBy = "asc";
        }

        var sortDirection = Sort.Direction.ASC;

        if (!orderBy.equals("asc")) {
            sortDirection = Sort.Direction.DESC;
        }

        return productRepository.findAll(Sort.by(sortDirection, sortBy));
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
