package com.github.jbence1994.webshop.user;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConfirmNewPasswordValidator implements ConstraintValidator<ConfirmNewPassword, ChangePasswordRequest> {

    @Override
    public void initialize(ConfirmNewPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(ChangePasswordRequest request, ConstraintValidatorContext constraintValidatorContext) {
        if (request == null || request.getNewPassword() == null || request.getConfirmNewPassword() == null) {
            return false;
        }

        if (request.getNewPassword().isBlank() || request.getConfirmNewPassword().isBlank()) {
            return false;
        }

        return request.getNewPassword().equals(request.getConfirmNewPassword());
    }
}
