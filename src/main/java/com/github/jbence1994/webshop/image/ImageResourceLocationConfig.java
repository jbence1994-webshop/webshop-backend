package com.github.jbence1994.webshop.image;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ImageResourceLocationConfig implements WebMvcConfigurer {
    private final ImageUploadsConfig imageUploadsConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        var productPhotosUploadDirectoryPath = imageUploadsConfig.productPhotosDirectory();
        var userAvatarUploadDirectoryPath = imageUploadsConfig.userAvatarDirectory();

        registry
                .addResourceHandler(
                        productPhotosUploadDirectoryPath + "/**",
                        userAvatarUploadDirectoryPath + "/**"
                )
                .addResourceLocations(
                        "file:" + productPhotosUploadDirectoryPath,
                        "file:" + userAvatarUploadDirectoryPath
                )
                .setCachePeriod(3600);
    }
}
