package com.github.jbence1994.webshop.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "webshop.profile-avatar-upload-directory-config")
@AllArgsConstructor
@Getter
public class ProfileAvatarUploadDirectoryConfig {
    private String path;
}
