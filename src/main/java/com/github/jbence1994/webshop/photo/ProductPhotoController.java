package com.github.jbence1994.webshop.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/products/{productId}/photos")
@Validated
@RequiredArgsConstructor
public class ProductPhotoController {
    private final ProductPhotoService productPhotoService;
    private final PhotoMapper photoMapper;

    @Value("${webshop.photo-upload-directory-path.products}")
    private String productPhotosUploadDirectoryPath;

    @PostMapping
    public ResponseEntity<PhotoResponse> uploadProductPhoto(
            @PathVariable Long productId,
            @FileNotEmpty @RequestParam("file") MultipartFile file
    ) {
        var photo = photoMapper.toPhoto(file);

        var photoFileName = productPhotoService.uploadPhoto(productId, photo);

        var url = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(productPhotosUploadDirectoryPath + "/")
                .path(photoFileName)
                .toUriString();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new PhotoResponse(photoFileName, url));

    }
}
