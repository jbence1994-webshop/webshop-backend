package com.github.jbence1994.webshop.common;

import org.springframework.validation.FieldError;

public class FieldErrorTestObject {
    public static FieldError fieldError() {
        return new FieldError(
                "CreateProductDto",
                "name",
                "Name must be not empty."
        );
    }
}
