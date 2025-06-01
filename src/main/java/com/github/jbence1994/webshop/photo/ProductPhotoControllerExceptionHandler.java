package com.github.jbence1994.webshop.photo;

import com.github.jbence1994.webshop.common.ErrorDto;
import com.github.jbence1994.webshop.product.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = ProductPhotoController.class)
public class ProductPhotoControllerExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(InvalidFileExtensionException.class)
    private ResponseEntity<ErrorDto> handleInvalidFileExtensionException(InvalidFileExtensionException exception) {
        return ResponseEntity.badRequest().body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(ProductPhotoUploadException.class)
    private ResponseEntity<ErrorDto> handleProductPhotoUploadException(ProductPhotoUploadException exception) {
        return ResponseEntity.internalServerError().body(new ErrorDto(exception.getMessage()));
    }
}
