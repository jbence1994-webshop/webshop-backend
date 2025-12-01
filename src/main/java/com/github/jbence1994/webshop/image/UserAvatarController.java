package com.github.jbence1994.webshop.image;

import com.github.jbence1994.webshop.user.UserQueryService;
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
@RequestMapping("/users/{id}/avatar")
@Validated
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
    public ResponseEntity<Void> uploadUserAvatar(
            @PathVariable Long id,
            @FileNotEmpty @RequestParam("file") MultipartFile file
    ) {
        var uploadImage = imageMapper.toImageUpload(file);
        imageService.uploadImage(id, uploadImage);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<?> getUserAvatar(@PathVariable Long id) {
        return userQueryService.getUser(id).getAvatarFileName()
                .map(userAvatar -> {
                    var url = imageUrlBuilder.buildUrl(userAvatar);
                    return ResponseEntity.ok(new ImageResponse(userAvatar, url));
                })
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<Void> deleteUserAvatar(
            @PathVariable Long id,
            @PathVariable String fileName
    ) {
        imageService.deleteImage(id, fileName);

        return ResponseEntity.noContent().build();
    }
}
