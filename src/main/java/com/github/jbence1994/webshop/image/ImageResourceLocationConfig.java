package com.github.jbence1994.webshop.image;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ImageResourceLocationConfig implements WebMvcConfigurer {
    private final ProductPhotosUploadDirectoryConfig productPhotosUploadDirectoryConfig;
    private final ProfileAvatarUploadDirectoryConfig profileAvatarUploadDirectoryConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        var productPhotosUploadDirectoryPath = productPhotosUploadDirectoryConfig.path();
        var profileAvatarUploadDirectoryPath = profileAvatarUploadDirectoryConfig.path();

        registry
                .addResourceHandler(
                        productPhotosUploadDirectoryPath + "/**",
                        profileAvatarUploadDirectoryPath + "/**"
                )
                .addResourceLocations(
                        "file:" + productPhotosUploadDirectoryPath,
                        "file:" + profileAvatarUploadDirectoryPath
                )
                .setCachePeriod(3600);
    }
}
