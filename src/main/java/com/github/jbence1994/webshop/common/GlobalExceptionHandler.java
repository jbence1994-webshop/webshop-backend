package com.github.jbence1994.webshop.common;

import com.github.jbence1994.webshop.photo.InvalidFileExtensionException;
import com.github.jbence1994.webshop.photo.PhotoDeletionException;
import com.github.jbence1994.webshop.photo.PhotoUploadException;
import com.github.jbence1994.webshop.user.ChangePasswordRequest;
import com.github.jbence1994.webshop.user.ConfirmNewPassword;
import com.github.jbence1994.webshop.user.ConfirmPassword;
import com.github.jbence1994.webshop.user.RegisterUserRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ErrorDto> handleMissingServletRequestPartException(MissingServletRequestPartException exception) {
        var fieldName = exception.getRequestPartName();
        return ResponseEntity.badRequest().body(new ErrorDto(String.format("Required part '%s' is missing.", fieldName)));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException exception) {
        var allMessages = exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));

        return ResponseEntity.badRequest().body(new ErrorDto(allMessages));
    }

    @ExceptionHandler(InvalidFileExtensionException.class)
    public ResponseEntity<ErrorDto> handleInvalidFileExtensionException(InvalidFileExtensionException exception) {
        return ResponseEntity.badRequest().body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(PhotoUploadException.class)
    public ResponseEntity<ErrorDto> handlePhotoUploadException(PhotoUploadException exception) {
        return ResponseEntity.internalServerError().body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(PhotoDeletionException.class)
    public ResponseEntity<ErrorDto> handlePhotoDeletionException(PhotoDeletionException exception) {
        return ResponseEntity.internalServerError().body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorDto>> handleValidationErrors(MethodArgumentNotValidException exception) {
        var validationErrors = new ArrayList<ValidationErrorDto>();

        exception.getBindingResult().getFieldErrors().forEach(error ->
                validationErrors.add(new ValidationErrorDto(error.getField(), error.getDefaultMessage()))
        );

        var confirmPasswordDefaultMessage = RegisterUserRequest.class.getAnnotation(ConfirmPassword.class).message();
        var confirmNewPasswordDefaultMessage = ChangePasswordRequest.class.getAnnotation(ConfirmNewPassword.class).message();

        exception.getBindingResult().getAllErrors().forEach(error -> {
            addIfMatches(validationErrors, error, "confirmPassword", confirmPasswordDefaultMessage);
            addIfMatches(validationErrors, error, "confirmNewPassword", confirmNewPasswordDefaultMessage);
        });

        return ResponseEntity.badRequest().body(validationErrors);
    }

    private void addIfMatches(
            List<ValidationErrorDto> validationErrors,
            ObjectError error,
            String fieldName,
            String expectedMessage
    ) {
        var message = error.getDefaultMessage();

        if (message != null && message.equals(expectedMessage)) {
            validationErrors.add(new ValidationErrorDto(fieldName, message));
        }
    }
}
