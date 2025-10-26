package com.github.jbence1994.webshop.user;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConfirmUserPasswordValidator implements ConstraintValidator<ConfirmUserPassword, RegistrationRequest> {

    @Override
    public void initialize(ConfirmUserPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(RegistrationRequest request, ConstraintValidatorContext constraintValidatorContext) {
        if (request.user() == null || request.user().password() == null || request.user().confirmPassword() == null) {
            return false;
        }

        if (request.user().password().isBlank() || request.user().confirmPassword().isBlank()) {
            return false;
        }

        return request.user().password().equals(request.user().confirmPassword());
    }
}
