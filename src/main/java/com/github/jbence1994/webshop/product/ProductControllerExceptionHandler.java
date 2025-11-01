package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.common.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductControllerExceptionHandler {

    @ExceptionHandler(exception = InvalidCategoryException.class)
    public ResponseEntity<ErrorDto> handleInvalidCategoryException(InvalidCategoryException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(exception = InvalidProductRatingValueException.class)
    public ResponseEntity<ErrorDto> handleInvalidProductRatingValueException(InvalidProductRatingValueException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(exception = ProductAlreadyRatedException.class)
    public ResponseEntity<ErrorDto> handleProductAlreadyRatedException(ProductAlreadyRatedException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(exception = ProductAlreadyOnWishlistException.class)
    public ResponseEntity<ErrorDto> handleProductAlreadyOnWishlistException(ProductAlreadyOnWishlistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(exception.getMessage()));
    }
}
