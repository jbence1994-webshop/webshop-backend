package com.github.jbence1994.webshop.image;

import com.github.jbence1994.webshop.common.ErrorDto;
import com.github.jbence1994.webshop.product.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = ProductPhotoController.class)
public class ProductPhotoControllerExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(InvalidFileExtensionException.class)
    public ResponseEntity<ErrorDto> handleInvalidFileExtensionException(InvalidFileExtensionException exception) {
        return ResponseEntity.badRequest().body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(ImageUploadException.class)
    public ResponseEntity<ErrorDto> handleImageUploadException(ImageUploadException exception) {
        return ResponseEntity.internalServerError().body(new ErrorDto(exception.getMessage()));
    }

    @ExceptionHandler(ImageDeletionException.class)
    public ResponseEntity<ErrorDto> handleImageDeletionException(ImageDeletionException exception) {
        return ResponseEntity.internalServerError().body(new ErrorDto(exception.getMessage()));
    }
}
