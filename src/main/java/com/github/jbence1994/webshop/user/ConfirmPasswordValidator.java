package com.github.jbence1994.webshop.user;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, RegisterUserRequest> {

    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(RegisterUserRequest request, ConstraintValidatorContext constraintValidatorContext) {
        if (request == null || request.getPassword() == null || request.getConfirmPassword() == null) {
            return false;
        }

        if (request.getPassword().isBlank() || request.getConfirmPassword().isBlank()) {
            return false;
        }

        return request.getPassword().equals(request.getConfirmPassword());
    }
}
