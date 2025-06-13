package com.github.jbence1994.webshop.common;

import org.springframework.validation.ObjectError;

public class ObjectErrorTestObject {
    public static ObjectError objectError() {
        return new ObjectError(
                "registerUserRequest",
                "Confirm password does not match the password."
        );
    }
}
