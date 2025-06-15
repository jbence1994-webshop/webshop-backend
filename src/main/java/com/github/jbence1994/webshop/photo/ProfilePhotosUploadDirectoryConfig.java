package com.github.jbence1994.webshop.photo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "webshop.profile-photos-upload-directory-config")
@AllArgsConstructor
@Getter
public class ProfilePhotosUploadDirectoryConfig {
    private String path;
}
