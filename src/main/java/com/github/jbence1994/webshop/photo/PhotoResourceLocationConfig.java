package com.github.jbence1994.webshop.photo;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class PhotoResourceLocationConfig implements WebMvcConfigurer {
    private final ProductPhotosUploadDirectoryPathConfig productPhotosUploadDirectoryPathConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        var path = productPhotosUploadDirectoryPathConfig.getPath();

        registry
                .addResourceHandler(path + "/**")
                .addResourceLocations("file:" + path)
                .setCachePeriod(3600);
    }
}
