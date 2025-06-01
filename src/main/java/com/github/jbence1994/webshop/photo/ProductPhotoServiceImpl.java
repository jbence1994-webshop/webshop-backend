package com.github.jbence1994.webshop.photo;

import com.github.jbence1994.webshop.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProductPhotoServiceImpl implements ProductPhotoService {
    private final FileExtensionsConfig fileExtensionsConfig;
    private final ProductService productService;
    private final FileUtils fileUtils;

    @Value("${webshop.photo-upload-directory-path.products}")
    private String productPhotosUploadDirectoryPath;

    @Override
    public String uploadPhoto(Long productId, Photo photo) {
        try {
            if (!hasValidExtension(photo)) {
                throw new InvalidFileExtensionException(photo.getFileExtension());
            }

            var product = productService.getProduct(productId);

            var fileName = photo.generateFileName();

            fileUtils.store(
                    productPhotosUploadDirectoryPath,
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

    private boolean hasValidExtension(Photo photo) {
        return fileExtensionsConfig.getAllowedFileExtensions().stream()
                .anyMatch(extension -> extension.equals(photo.getFileExtension()));
    }
}
