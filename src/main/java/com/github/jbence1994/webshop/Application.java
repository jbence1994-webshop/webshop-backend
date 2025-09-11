package com.github.jbence1994.webshop;

import com.github.jbence1994.webshop.cart.FreeShippingConfig;
import com.github.jbence1994.webshop.common.ClientAppConfig;
import com.github.jbence1994.webshop.common.WebshopEmailAddressConfig;
import com.github.jbence1994.webshop.common.WebshopNameConfig;
import com.github.jbence1994.webshop.image.ImageUploadsConfig;
import com.github.jbence1994.webshop.loyalty.LoyaltyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {
        WebshopEmailAddressConfig.class,
        ImageUploadsConfig.class,
        FreeShippingConfig.class,
        WebshopNameConfig.class,
        ClientAppConfig.class,
        LoyaltyConfig.class
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
