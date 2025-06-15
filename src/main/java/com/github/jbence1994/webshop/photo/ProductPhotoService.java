package com.github.jbence1994.webshop.photo;

import com.github.jbence1994.webshop.product.ProductQueryService;
import com.github.jbence1994.webshop.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductPhotoService implements PhotoService {
    private final ProductPhotosUploadDirectoryConfig productPhotosUploadDirectoryConfig;
    private final ProductPhotoQueryService productPhotoQueryService;
    private final ProductQueryService productQueryService;
    private final ProductService productService;
    private final FileExtensionValidator fileExtensionValidator;
    private final FileNameGenerator fileNameGenerator;
    private final FileUtils fileUtils;

    @Override
    public DownloadPhotoDto uploadPhoto(Long productId, UploadPhotoDto uploadPhotoDto) {
        try {
            fileExtensionValidator.validate(uploadPhotoDto);

            var product = productQueryService.getProduct(productId);

            var fileName = fileNameGenerator.generate(uploadPhotoDto.getFileExtension());

            fileUtils.store(
                    productPhotosUploadDirectoryConfig.getPath(),
                    fileName,
                    uploadPhotoDto.getInputStream()
            );

            product.addPhoto(fileName);
            productService.updateProduct(product);

            return new DownloadPhotoDto(fileName);
        } catch (FileUploadException exception) {
            throw new ProductPhotoUploadException();
        }
    }

    @Override
    public List<DownloadPhotoDto> getPhotos(Long productId) {
        return productPhotoQueryService.getProductPhotos(productId).stream()
                .map(productPhoto -> new DownloadPhotoDto(productPhoto.getFileName()))
                .toList();
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
