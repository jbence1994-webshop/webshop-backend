package com.github.jbence1994.webshop.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class ProfileAvatarUrlBuilder implements ImageUrlBuilder {
    private final ProfileAvatarUploadDirectoryConfig profileAvatarUploadDirectoryConfig;

    @Override
    public String buildUrl(String fileName) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(profileAvatarUploadDirectoryConfig.path() + "/")
                .path(fileName)
                .toUriString();
    }
}
