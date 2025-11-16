package com.github.jbence1994.webshop.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class UserAvatarUrlBuilder implements ImageUrlBuilder {
    private final ImageUploadsConfig imageUploadsConfig;

    @Override
    public String buildUrl(String fileName) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(imageUploadsConfig.userAvatarDirectory() + "/")
                .path(fileName)
                .toUriString();
    }
}
