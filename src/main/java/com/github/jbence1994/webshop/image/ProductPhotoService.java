package com.github.jbence1994.webshop.image;

import com.github.jbence1994.webshop.product.ProductQueryService;
import com.github.jbence1994.webshop.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductPhotoService implements PhotoService {
    private final ProductPhotosUploadDirectoryConfig productPhotosUploadDirectoryConfig;
    private final ProductQueryService productQueryService;
    private final ProductService productService;
    private final FileExtensionValidator fileExtensionValidator;
    private final FileNameGenerator fileNameGenerator;
    private final FileUtils fileUtils;

    @Override
    public String uploadPhoto(Long productId, UploadPhoto uploadPhoto) {
        try {
            fileExtensionValidator.validate(uploadPhoto);

            var product = productQueryService.getProduct(productId);

            var fileName = fileNameGenerator.generate(uploadPhoto.getFileExtension());

            fileUtils.store(
                    productPhotosUploadDirectoryConfig.getPath(),
                    fileName,
                    uploadPhoto.getInputStream()
            );

            product.addPhoto(fileName);
            productService.updateProduct(product);

            return fileName;
        } catch (FileUploadException exception) {
            throw new ProductPhotoUploadException();
        }
    }

    @Override
    public void deletePhoto(Long productId, String fileName) {
        try {
            var product = productQueryService.getProduct(productId);

            fileUtils.remove(
                    productPhotosUploadDirectoryConfig.getPath(),
                    fileName
            );

            product.removePhoto(fileName);
            productService.updateProduct(product);
        } catch (FileDeletionException exception) {
            throw new ProductPhotoDeletionException();
        }
    }
}
