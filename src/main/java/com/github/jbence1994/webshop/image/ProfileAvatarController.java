package com.github.jbence1994.webshop.image;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users/{userId}/profile/avatar")
@Validated
public class ProfileAvatarController {
    private final ImageService imageService;
    private final ImageMapper imageMapper;
    private final ImageUrlBuilder imageUrlBuilder;

    public ProfileAvatarController(
            @Qualifier("profileAvatarService") final ImageService imageService,
            final ImageMapper imageMapper,
            final ImageUrlBuilder imageUrlBuilder
    ) {
        this.imageService = imageService;
        this.imageMapper = imageMapper;
        this.imageUrlBuilder = imageUrlBuilder;
    }

    @PostMapping
    public ResponseEntity<ImageResponse> uploadProfileAvatar(
            @PathVariable Long userId,
            @FileNotEmpty @RequestParam("file") MultipartFile file
    ) {
        var uploadImage = imageMapper.toUploadImage(file);
        var uploadedImageFileName = imageService.uploadImage(userId, uploadImage);

        var url = imageUrlBuilder.buildUrl(uploadedImageFileName);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ImageResponse(uploadedImageFileName, url));
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<Void> deleteProfileAvatar(
            @PathVariable Long userId,
            @PathVariable String fileName
    ) {
        imageService.deleteImage(userId, fileName);

        return ResponseEntity.noContent().build();
    }
}
