package com.github.jbence1994.webshop;

import com.github.jbence1994.webshop.photo.FileExtensionsConfig;
import com.github.jbence1994.webshop.photo.ProductPhotosUploadDirectoryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {
        FileExtensionsConfig.class,
        ProductPhotosUploadDirectoryConfig.class
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
