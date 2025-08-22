package com.github.jbence1994.webshop.image;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "webshop.profile-avatar-upload-directory-config")
public record ProfileAvatarUploadDirectoryConfig(String path) {
}
