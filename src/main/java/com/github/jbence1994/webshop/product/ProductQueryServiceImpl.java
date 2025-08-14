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
    public List<Product> getProducts(
            String sortBy,
            String orderBy,
            int page,
            int size,
            Byte categoryId
    ) {
        var sortProperties = getSortProperties(sortBy);
        var sortDirection = getSortDirection(orderBy);
        var pageNumber = getPageNumber(page);

        var products = productRepository
                .findAll(PageRequest.of(pageNumber, size, Sort.by(sortDirection, sortProperties)));

        if (categoryId == null) {
            return products.toList();
        }

        return products.stream()
                .filter(product -> categoryId.equals(product.getCategory().getId()))
                .toList();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    private String getSortProperties(String sortBy) {
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

    private int getPageNumber(int page) {
        if (page <= 0 || page == 1) {
            return 0;
        }

        return page - 1;
    }
}
