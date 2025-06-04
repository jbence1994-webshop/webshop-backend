package com.github.jbence1994.webshop.product;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> getProducts(String sortBy, String orderBy, int page, int size) {
        var sortByProperties = getSortByProperties(sortBy);
        var sortDirection = getSortDirection(orderBy);

        return productRepository
                .findAll(PageRequest.of(page, size, Sort.by(sortDirection, sortByProperties)))
                .toList();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public boolean isProductExistById(Long id) {
        return productRepository.existsById(id);
    }

    private String getSortByProperties(String sortBy) {
        if (sortBy == null || !Set.of("id", "price").contains(sortBy)) {
            sortBy = "id";
        }

        return sortBy;
    }

    private Sort.Direction getSortDirection(String orderBy) {
        if (orderBy == null || !Set.of("asc", "desc").contains(orderBy)) {
            orderBy = "asc";
        }

        var sortDirection = Sort.Direction.ASC;

        if (!orderBy.equals("asc")) {
            sortDirection = Sort.Direction.DESC;
        }

        return sortDirection;
    }
}
