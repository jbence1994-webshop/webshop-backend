package com.github.jbence1994.webshop.photo;

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
    private final PhotoService photoService;
    private final ProductPhotoQueryService productPhotoQueryService;
    private final PhotoMapper photoMapper;
    private final PhotoUrlBuilder photoUrlBuilder;

    public ProductPhotoController(
            @Qualifier("productPhotoService") final PhotoService photoService,
            final ProductPhotoQueryService productPhotoQueryService,
            final PhotoMapper photoMapper,
            final PhotoUrlBuilder photoUrlBuilder
    ) {
        this.photoService = photoService;
        this.productPhotoQueryService = productPhotoQueryService;
        this.photoMapper = photoMapper;
        this.photoUrlBuilder = photoUrlBuilder;
    }

    @PostMapping
    public ResponseEntity<PhotoResponse> uploadProductPhoto(
            @PathVariable Long productId,
            @FileNotEmpty @RequestParam("file") MultipartFile file
    ) {
        var uploadPhoto = photoMapper.toUploadPhoto(file);
        var uploadedPhotoFileName = photoService.uploadPhoto(productId, uploadPhoto);

        var url = photoUrlBuilder.buildUrl(uploadedPhotoFileName);

        return ResponseEntity.status(HttpStatus.CREATED).body(new PhotoResponse(uploadedPhotoFileName, url));

    }

    @GetMapping
    public List<PhotoResponse> getProductPhotos(@PathVariable Long productId) {
        var productPhotos = productPhotoQueryService.getProductPhotos(productId);

        // FIXME: Use Mapstruct here.
        return productPhotos.stream()
                .map(productPhoto -> {
                    var fileName = productPhoto.getFileName();
                    var url = photoUrlBuilder.buildUrl(fileName);

                    return new PhotoResponse(fileName, url);
                })
                .toList();
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<Void> deleteProductPhoto(
            @PathVariable Long productId,
            @PathVariable String fileName
    ) {
        photoService.deletePhoto(productId, fileName);

        return ResponseEntity.noContent().build();
    }
}
