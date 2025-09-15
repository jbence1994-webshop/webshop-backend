package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.cart.CartNotFoundException;
import com.github.jbence1994.webshop.common.ErrorDto;
import com.github.jbence1994.webshop.coupon.CouponAlreadyRedeemedException;
import com.github.jbence1994.webshop.coupon.CouponNotFoundException;
import com.github.jbence1994.webshop.coupon.ExpiredCouponException;
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

    @ExceptionHandler(exception = CheckoutSessionAlreadyCompletedException.class)
    public ResponseEntity<ErrorDto> handleCheckoutSessionAlreadyCompletedException(CheckoutSessionAlreadyCompletedException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(exception = CartNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCartNotFoundException(CartNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(exception.getMessage()));
    }

    // FIXME: Need to refactor what kind of exception occurred => customize Http status codes.
    @ExceptionHandler(exception = PaymentException.class)
    public ResponseEntity<ErrorDto> handlePaymentException(PaymentException exception) {
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(new ErrorDto(exception.getMessage()));
    }
}
