package com.github.jbence1994.webshop.photo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PhotoResourceLocationConfig implements WebMvcConfigurer {

    @Value("${webshop.photo-upload-directory-path.products}")
    private String productPhotosUploadDirectoryPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(productPhotosUploadDirectoryPath + "/**")
                .addResourceLocations("file:" + productPhotosUploadDirectoryPath)
                .setCachePeriod(3600);
    }
}
