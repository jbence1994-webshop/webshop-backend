package com.github.jbence1994.webshop.photo;

import com.github.jbence1994.webshop.product.Product;
import com.github.jbence1994.webshop.product.ProductQueryService;
import com.github.jbence1994.webshop.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPhotoService extends PhotoService<Product, ProductPhoto> {
    private final ProductPhotosUploadDirectoryConfig productPhotosUploadDirectoryConfig;
    private final ProductPhotoQueryService productPhotoQueryService;
    private final ProductQueryService productQueryService;
    private final ProductRepository productRepository;

    public ProductPhotoService(
            final FileExtensionValidator fileExtensionValidator,
            final FileNameGenerator fileNameGenerator,
            final FileUtils fileUtils,

            final ProductPhotosUploadDirectoryConfig productPhotosUploadDirectoryConfig,
            final ProductPhotoQueryService productPhotoQueryService,
            final ProductQueryService productQueryService,
            final ProductRepository productRepository
    ) {
        super(fileExtensionValidator, fileNameGenerator, fileUtils);

        this.productPhotosUploadDirectoryConfig = productPhotosUploadDirectoryConfig;
        this.productPhotoQueryService = productPhotoQueryService;
        this.productQueryService = productQueryService;
        this.productRepository = productRepository;
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
        productRepository.save(product);
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
