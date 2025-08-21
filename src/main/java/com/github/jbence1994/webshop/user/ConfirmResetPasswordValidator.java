package com.github.jbence1994.webshop.user;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConfirmResetPasswordValidator implements ConstraintValidator<ConfirmResetPassword, ConfirmResetPasswordRequest> {
    @Override
    public void initialize(ConfirmResetPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(ConfirmResetPasswordRequest request, ConstraintValidatorContext constraintValidatorContext) {
        if (request == null || request.getNewPassword() == null || request.getConfirmNewPassword() == null) {
            return false;
        }

        if (request.getNewPassword().isBlank() || request.getConfirmNewPassword().isBlank()) {
            return false;
        }

        return request.getNewPassword().equals(request.getConfirmNewPassword());
    }
}
