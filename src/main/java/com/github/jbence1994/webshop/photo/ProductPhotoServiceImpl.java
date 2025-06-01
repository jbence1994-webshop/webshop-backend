package com.github.jbence1994.webshop.photo;

import com.github.jbence1994.webshop.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductPhotoServiceImpl implements ProductPhotoService {
    private final FileExtensionsConfig fileExtensionsConfig;
    private final ProductService productService;
    private final FileUtils fileUtils;

    @Value("${webshop.photo-upload-directory-path.products}")
    private String productPhotosUploadDirectoryPath;

    @Override
    public String uploadProductPhoto(Long id, Photo photo) {
        try {
            if (!hasValidExtension(photo)) {
                throw new InvalidFileExtensionException(photo.getFileExtension());
            }

            var product = productService.getProduct(id);

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

    @Override
    public List<ProductPhoto> getProductPhotos(Long id) {
        return productService.getProduct(id).getPhotos().stream().toList();
    }

    private boolean hasValidExtension(Photo photo) {
        return fileExtensionsConfig.getAllowedFileExtensions().stream()
                .anyMatch(extension -> extension.equals(photo.getFileExtension()));
    }
}
