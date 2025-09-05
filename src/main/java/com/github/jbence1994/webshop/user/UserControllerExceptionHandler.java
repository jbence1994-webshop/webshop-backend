package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = UserController.class)
public class UserControllerExceptionHandler {

    @ExceptionHandler(exception = {
            EmailAlreadyExistsException.class,
            PhoneNumberAlreadyExistsException.class,
    })
    public ResponseEntity<ErrorResponse> handleEmailOrPhoneNumberAlreadyExistsException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(exception = {
            InvalidTemporaryPasswordException.class,
            ExpiredTemporaryPasswordException.class
    })
    public ResponseEntity<ErrorResponse> handleInvalidTemporaryPasswordOrExpiredTemporaryPasswordException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(exception.getMessage()));
    }
}
