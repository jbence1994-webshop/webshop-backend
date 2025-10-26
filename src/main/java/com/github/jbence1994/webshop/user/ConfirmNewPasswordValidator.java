package com.github.jbence1994.webshop.user;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConfirmNewPasswordValidator implements ConstraintValidator<ConfirmNewPassword, ChangePasswordRequest> {

    @Override
    public void initialize(ConfirmNewPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(ChangePasswordRequest request, ConstraintValidatorContext constraintValidatorContext) {
        if (request == null || request.newPassword() == null || request.confirmNewPassword() == null) {
            return false;
        }

        if (request.newPassword().isBlank() || request.confirmNewPassword().isBlank()) {
            return false;
        }

        return request.newPassword().equals(request.confirmNewPassword());
    }
}
