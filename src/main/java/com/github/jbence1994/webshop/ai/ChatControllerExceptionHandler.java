package com.github.jbence1994.webshop.ai;

import com.github.jbence1994.webshop.common.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = ChatController.class)
public class ChatControllerExceptionHandler {

    @ExceptionHandler(exception = OllamaException.class)
    public ResponseEntity<ErrorDto> handleOllamaException(OllamaException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto(exception.getMessage()));
    }
}
