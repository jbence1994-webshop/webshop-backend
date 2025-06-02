package com.github.jbence1994.webshop.photo;

import com.github.jbence1994.webshop.product.ProductNotFoundException;
import com.github.jbence1994.webshop.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductPhotoQueryServiceImpl implements ProductPhotoQueryService {
    private final ProductService productService;
    private final ProductPhotoRepository productPhotoRepository;

    @Override
    public List<ProductPhoto> getProductPhotos(Long productId) {
        if (!productService.isProductExistById(productId)) {
            throw new ProductNotFoundException(productId);
        }

        return productPhotoRepository.findAllByProductId(productId);
    }
}
