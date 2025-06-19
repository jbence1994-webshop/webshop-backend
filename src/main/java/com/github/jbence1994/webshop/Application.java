package com.github.jbence1994.webshop;

import com.github.jbence1994.webshop.auth.JwtConfig;
import com.github.jbence1994.webshop.image.FileExtensionsConfig;
import com.github.jbence1994.webshop.image.ProductPhotosUploadDirectoryConfig;
import com.github.jbence1994.webshop.image.ProfileAvatarUploadDirectoryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {
        JwtConfig.class,
        FileExtensionsConfig.class,
        ProductPhotosUploadDirectoryConfig.class,
        ProfileAvatarUploadDirectoryConfig.class
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
