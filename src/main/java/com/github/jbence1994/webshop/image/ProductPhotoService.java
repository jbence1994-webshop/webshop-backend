package com.github.jbence1994.webshop.image;

import com.github.jbence1994.webshop.product.ProductQueryService;
import com.github.jbence1994.webshop.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductPhotoService implements ImageService {
    private final ProductPhotosUploadDirectoryConfig productPhotosUploadDirectoryConfig;
    private final ProductQueryService productQueryService;
    private final ProductService productService;
    private final FileExtensionValidator fileExtensionValidator;
    private final FileNameGenerator fileNameGenerator;
    private final FileUtils fileUtils;

    @Override
    public String uploadImage(Long productId, ImageUpload image) {
        try {
            fileExtensionValidator.validate(image);

            var product = productQueryService.getProduct(productId);

            var fileName = fileNameGenerator.generate(image.getFileExtension());

            fileUtils.store(
                    productPhotosUploadDirectoryConfig.path(),
                    fileName,
                    image.getInputStream()
            );

            product.addPhoto(fileName);
            productService.updateProduct(product);

            return fileName;
        } catch (FileUploadException exception) {
            throw new ImageUploadException("The photo could not be uploaded successfully.");
        }
    }

    @Override
    public void deleteImage(Long productId, String fileName) {
        try {
            var product = productQueryService.getProduct(productId);

            fileUtils.remove(
                    productPhotosUploadDirectoryConfig.path(),
                    fileName
            );

            product.removePhoto(fileName);
            productService.updateProduct(product);
        } catch (FileDeletionException exception) {
            throw new ImageDeletionException("The photo could not be deleted successfully.");
        }
    }
}
