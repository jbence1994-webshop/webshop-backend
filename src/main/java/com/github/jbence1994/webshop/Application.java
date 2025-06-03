package com.github.jbence1994.webshop;

import com.github.jbence1994.webshop.photo.FileExtensionsConfig;
import com.github.jbence1994.webshop.photo.ProductPhotosUploadDirectoryPathConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {
        FileExtensionsConfig.class,
        ProductPhotosUploadDirectoryPathConfig.class
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
