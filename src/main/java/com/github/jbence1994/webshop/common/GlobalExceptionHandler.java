package com.github.jbence1994.webshop.common;

import com.github.jbence1994.webshop.user.ConfirmPassword;
import com.github.jbence1994.webshop.user.RegisterUserRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorDto>> handleValidationErrors(MethodArgumentNotValidException exception) {
        var validationErrors = new ArrayList<ValidationErrorDto>();

        exception.getBindingResult().getFieldErrors().forEach(error ->
                validationErrors.add(new ValidationErrorDto(error.getField(), error.getDefaultMessage()))
        );

        exception.getBindingResult().getAllErrors().forEach(error -> {
            var confirmPasswordAnnotation = RegisterUserRequest.class.getAnnotation(ConfirmPassword.class);

            if (error.getDefaultMessage() != null && confirmPasswordAnnotation != null && error.getDefaultMessage().equals(confirmPasswordAnnotation.message())) {
                validationErrors.add(new ValidationErrorDto("password", error.getDefaultMessage()));
            }
        });

        return ResponseEntity.badRequest().body(validationErrors);
    }
}
