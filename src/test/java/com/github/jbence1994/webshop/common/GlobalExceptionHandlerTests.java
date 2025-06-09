package com.github.jbence1994.webshop.common;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.Collections;
import java.util.List;

import static com.github.jbence1994.webshop.common.FieldErrorTestObject.fieldError;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTests {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void handleMissingServletRequestPartExceptionTest() {
        var exception = new MissingServletRequestPartException("file");

        var result = globalExceptionHandler.handleMissingServletRequestPartException(exception);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo("Required part 'file' is missing."));
    }

    @Test
    public void handleConstraintViolationExceptionTest() {
        ConstraintViolation<?> constraintViolation = mock(ConstraintViolation.class);
        var constraintViolations = Collections.singleton(constraintViolation);
        var exception = new ConstraintViolationException(constraintViolations);
        when(constraintViolation.getMessage()).thenReturn("The file must not be empty.");

        var result = globalExceptionHandler.handleConstraintViolationException(exception);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo("The file must not be empty."));
    }

    @Test
    public void handleValidationErrorsTest() {
        var bindingResult = mock(BindingResult.class);
        var exception = mock(MethodArgumentNotValidException.class);

        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError()));
        when(exception.getBindingResult()).thenReturn(bindingResult);

        var result = globalExceptionHandler.handleValidationErrors(exception);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().size(), equalTo(1));
    }
}
