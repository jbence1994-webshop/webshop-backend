package com.github.jbence1994.webshop.cart;

import com.github.jbence1994.webshop.common.ErrorDto;
import com.github.jbence1994.webshop.coupon.CouponAlreadyRedeemedException;
import com.github.jbence1994.webshop.coupon.CouponNotFoundException;
import com.github.jbence1994.webshop.coupon.ExpiredCouponException;
import com.github.jbence1994.webshop.product.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = CartController.class)
public class CartControllerExceptionHandler {

    @ExceptionHandler(exception = {
            CartNotFoundException.class,
            CartItemNotFoundException.class
    })
    public ResponseEntity<ErrorDto> handleCartOrCartItemNotFoundException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(exception = ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(exception = CouponNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCouponNotFoundException(CouponNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(exception = ExpiredCouponException.class)
    public ResponseEntity<ErrorDto> handleExpiredCouponException(ExpiredCouponException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(exception = CouponAlreadyRedeemedException.class)
    public ResponseEntity<ErrorDto> handleCouponAlreadyRedeemedException(CouponAlreadyRedeemedException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto(exception.getMessage()));
    }
}
