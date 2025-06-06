package com.github.jbence1994.webshop.photo;

import com.github.jbence1994.webshop.product.Product;
import com.github.jbence1994.webshop.product.ProductQueryService;
import com.github.jbence1994.webshop.product.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPhotoService extends PhotoService<Product, ProductPhoto> {
    private final ProductPhotosUploadDirectoryConfig productPhotosUploadDirectoryConfig;
    private final ProductPhotoQueryService productPhotoQueryService;
    private final ProductQueryService productQueryService;
    private final ProductService productService;

    public ProductPhotoService(
            final FileExtensionValidator fileExtensionValidator,
            final FileNameGenerator fileNameGenerator,
            final FileUtils fileUtils,

            final ProductPhotosUploadDirectoryConfig productPhotosUploadDirectoryConfig,
            final ProductPhotoQueryService productPhotoQueryService,
            final ProductQueryService productQueryService,
            final ProductService productService
    ) {
        super(fileExtensionValidator, fileNameGenerator, fileUtils);

        this.productPhotosUploadDirectoryConfig = productPhotosUploadDirectoryConfig;
        this.productPhotoQueryService = productPhotoQueryService;
        this.productQueryService = productQueryService;
        this.productService = productService;
    }

    @Override
    public String getPhotosUploadDirectoryPath() {
        return productPhotosUploadDirectoryConfig.getPath();
    }

    @Override
    public Product getEntity(Long productId) {
        return productQueryService.getProduct(productId);
    }

    @Override
    public void updateEntity(Product product) {
        productService.updateProduct(product);
    }

    @Override
    public void addPhotoToEntity(Product product, String fileName) {
        product.addPhoto(fileName);
    }

    @Override
    public void removePhotoFromEntity(Product product, String fileName) {
        product.removePhoto(fileName);
    }

    @Override
    public List<ProductPhoto> getEntityPhotos(Long productId) {
        return productPhotoQueryService.getProductPhotos(productId);
    }

    @Override
    public RuntimeException photoUploadException() {
        return new ProductPhotoUploadException();
    }

    @Override
    public RuntimeException photoDeletionException() {
        return new ProductPhotoDeletionException();
    }
}
