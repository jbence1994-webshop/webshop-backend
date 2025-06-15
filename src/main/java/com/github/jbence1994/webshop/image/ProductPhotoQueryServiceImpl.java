package com.github.jbence1994.webshop.image;

import com.github.jbence1994.webshop.product.ProductQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductPhotoQueryServiceImpl implements ProductPhotoQueryService {
    private final ProductQueryService productQueryService;
    private final ProductPhotoRepository productPhotoRepository;

    @Override
    public List<ProductPhoto> getProductPhotos(Long productId) {
        var product = productQueryService.getProduct(productId);
        return productPhotoRepository.findAllByProductId(product.getId());
    }
}
