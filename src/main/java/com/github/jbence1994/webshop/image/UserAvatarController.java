package com.github.jbence1994.webshop.image;

import com.github.jbence1994.webshop.user.UserQueryService;
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

@RestController
@RequestMapping("/users/{userId}/avatar")
@Validated
@Slf4j
public class UserAvatarController {
    private final UserQueryService userQueryService;
    private final ImageUrlBuilder imageUrlBuilder;
    private final ImageService imageService;
    private final ImageMapper imageMapper;

    public UserAvatarController(
            final UserQueryService userQueryService,
            @Qualifier("userAvatarUrlBuilder") final ImageUrlBuilder imageUrlBuilder,
            @Qualifier("userAvatarService") final ImageService imageService,
            final ImageMapper imageMapper
    ) {
        this.userQueryService = userQueryService;
        this.imageUrlBuilder = imageUrlBuilder;
        this.imageService = imageService;
        this.imageMapper = imageMapper;
    }

    @PostMapping
    public ResponseEntity<ImageResponse> uploadUserAvatar(
            @PathVariable Long userId,
            @FileNotEmpty @RequestParam("file") MultipartFile file
    ) {
        var uploadImage = imageMapper.toImageUpload(file);
        var uploadedImageFileName = imageService.uploadImage(userId, uploadImage);

        var url = imageUrlBuilder.buildUrl(uploadedImageFileName);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ImageResponse(uploadedImageFileName, url));
    }

    @GetMapping
    public ResponseEntity<?> getUserAvatar(@PathVariable Long userId) {
        return userQueryService.getUser(userId).getAvatarFileName()
                .map(userAvatar -> {
                    var url = imageUrlBuilder.buildUrl(userAvatar);
                    return ResponseEntity.ok(new ImageResponse(userAvatar, url));
                })
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<Void> deleteUserAvatar(
            @PathVariable Long userId,
            @PathVariable String fileName
    ) {
        imageService.deleteImage(userId, fileName);

        return ResponseEntity.noContent().build();
    }
}
