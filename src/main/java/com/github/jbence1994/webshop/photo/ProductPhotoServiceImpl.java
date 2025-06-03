package com.github.jbence1994.webshop.photo;

import com.github.jbence1994.webshop.product.ProductQueryService;
import com.github.jbence1994.webshop.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductPhotoServiceImpl implements ProductPhotoService {
    private final FileExtensionValidator fileExtensionValidator;
    private final ProductPhotosUploadDirectoryPathConfig productPhotosUploadDirectoryPathConfig;
    private final ProductQueryService productQueryService;
    private final ProductService productService;
    private final ProductPhotoQueryService productPhotoQueryService;
    private final FileUtils fileUtils;

    @Override
    public String uploadProductPhoto(Long productId, Photo photo) {
        try {
            fileExtensionValidator.validate(photo);

            var product = productQueryService.getProduct(productId);

            var fileName = photo.generateFileName();

            fileUtils.store(
                    productPhotosUploadDirectoryPathConfig.getPath(),
                    fileName,
                    photo.getInputStream()
            );

            product.addPhoto(fileName);
            productService.updateProduct(product);

            return fileName;
        } catch (IOException exception) {
            throw new ProductPhotoUploadException();
        }
    }

    @Override
    public List<ProductPhoto> getProductPhotos(Long productId) {
        return productPhotoQueryService.getProductPhotos(productId);
    }

    @Override
    public void deleteProductPhoto(Long productId, String fileName) {
        try {
            var product = productQueryService.getProduct(productId);

            fileUtils.remove(
                    productPhotosUploadDirectoryPathConfig.getPath(),
                    fileName
            );

            product.removePhoto(fileName);
            productService.updateProduct(product);
        } catch (IOException exception) {
            throw new ProductPhotoDeletionException();
        }
    }
}
