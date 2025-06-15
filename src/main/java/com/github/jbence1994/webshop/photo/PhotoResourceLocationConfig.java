package com.github.jbence1994.webshop.photo;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class PhotoResourceLocationConfig implements WebMvcConfigurer {
    private final ProductPhotosUploadDirectoryConfig productPhotosUploadDirectoryConfig;
    private final ProfilePhotosUploadDirectoryConfig profilePhotosUploadDirectoryConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        var productPhotosUploadDirectoryPath = productPhotosUploadDirectoryConfig.getPath();
        var profilePhotosUploadDirectoryPath = profilePhotosUploadDirectoryConfig.getPath();

        registry
                .addResourceHandler(
                        productPhotosUploadDirectoryPath + "/**",
                        profilePhotosUploadDirectoryPath + "/**"
                )
                .addResourceLocations(
                        "file:" + productPhotosUploadDirectoryPath,
                        "file:" + profilePhotosUploadDirectoryPath
                )
                .setCachePeriod(3600);
    }
}
