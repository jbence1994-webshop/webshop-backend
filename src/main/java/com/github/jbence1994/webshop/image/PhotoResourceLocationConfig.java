package com.github.jbence1994.webshop.image;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class PhotoResourceLocationConfig implements WebMvcConfigurer {
    private final ProductPhotosUploadDirectoryConfig productPhotosUploadDirectoryConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        var path = productPhotosUploadDirectoryConfig.getPath();

        registry
                .addResourceHandler(path + "/**")
                .addResourceLocations("file:" + path)
                .setCachePeriod(3600);
    }
}
