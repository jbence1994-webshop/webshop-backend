package com.github.jbence1994.webshop.image;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ProductPhotoController {
    private final ProductPhotoQueryService productPhotoQueryService;
    private final ImageUrlBuilder imageUrlBuilder;
    private final ImageService imageService;
    private final ImageMapper imageMapper;

    public ProductPhotoController(
            final ProductPhotoQueryService productPhotoQueryService,
            @Qualifier("productPhotoUrlBuilder") final ImageUrlBuilder imageUrlBuilder,
            @Qualifier("productPhotoService") final ImageService imageService,
            final ImageMapper imageMapper
    ) {
        this.productPhotoQueryService = productPhotoQueryService;
        this.imageUrlBuilder = imageUrlBuilder;
        this.imageService = imageService;
        this.imageMapper = imageMapper;
    }

    @PostMapping
    public ResponseEntity<ImageResponse> uploadProductPhoto(
            @PathVariable Long productId,
            @FileNotEmpty @RequestParam("file") MultipartFile file
    ) {
        var uploadImage = imageMapper.toImageUpload(file);
        var uploadedImageFileName = imageService.uploadImage(productId, uploadImage);

        var url = imageUrlBuilder.buildUrl(uploadedImageFileName);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ImageResponse(uploadedImageFileName, url));

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
