package com.github.jbence1994.webshop.order;

import com.github.jbence1994.webshop.common.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = OrderController.class)
public class OrderControllerExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorDto> handleOrderNotFoundException(OrderNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(exception.getMessage()));
    }
}
