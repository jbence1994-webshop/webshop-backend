package com.github.jbence1994.webshop.image;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products/{productId}/photos")
@Validated
public class ProductPhotoController {
    private final ImageService imageService;
    private final ProductPhotoQueryService productPhotoQueryService;
    private final ImageMapper imageMapper;
    private final ImageUrlBuilder imageUrlBuilder;

    public ProductPhotoController(
            @Qualifier("productPhotoService") final ImageService imageService,
            final ProductPhotoQueryService productPhotoQueryService,
            final ImageMapper imageMapper,
            final ImageUrlBuilder imageUrlBuilder
    ) {
        this.imageService = imageService;
        this.productPhotoQueryService = productPhotoQueryService;
        this.imageMapper = imageMapper;
        this.imageUrlBuilder = imageUrlBuilder;
    }

    @PostMapping
    public ResponseEntity<ImageResponse> uploadProductPhoto(
            @PathVariable Long productId,
            @FileNotEmpty @RequestParam("file") MultipartFile file
    ) {
        var uploadImage = imageMapper.toUploadImage(file);
        var uploadedPhotoFileName = imageService.uploadImage(productId, uploadImage);

        var url = imageUrlBuilder.buildUrl(uploadedPhotoFileName);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ImageResponse(uploadedPhotoFileName, url));

    }

    @GetMapping
    public List<ImageResponse> getProductPhotos(@PathVariable Long productId) {
        var productPhotos = productPhotoQueryService.getProductPhotos(productId);
        return imageMapper.toImageResponses(productPhotos, imageUrlBuilder);
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<Void> deleteProductPhoto(
            @PathVariable Long productId,
            @PathVariable String fileName
    ) {
        imageService.deleteImage(productId, fileName);

        return ResponseEntity.noContent().build();
    }
}
