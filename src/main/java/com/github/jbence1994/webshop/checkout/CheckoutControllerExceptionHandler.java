package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.cart.CartNotFoundException;
import com.github.jbence1994.webshop.common.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CheckoutControllerExceptionHandler {

    @ExceptionHandler(exception = CheckoutSessionNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCheckoutSessionNotFoundException(CheckoutSessionNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(exception = CheckoutSessionAlreadyCompletedException.class)
    public ResponseEntity<ErrorDto> handleCheckoutSessionAlreadyCompletedException(CheckoutSessionAlreadyCompletedException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(exception = CartNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCartNotFoundException(CartNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(exception.getMessage()));
    }
}
