package com.github.jbence1994.webshop.coupon;

import com.github.jbence1994.webshop.common.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponControllerExceptionHandler {

    @ExceptionHandler(exception = CouponNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCouponNotFoundException(CouponNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(exception.getMessage()));
    }
}
