package com.github.jbence1994.webshop.photo;

import com.github.jbence1994.webshop.product.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static com.github.jbence1994.webshop.photo.PhotoTestConstants.JPEG;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ProductPhotoControllerExceptionHandlerTests {

    @InjectMocks
    private ProductPhotoControllerExceptionHandler productPhotoControllerExceptionHandler;

    @Test
    public void handleProductNotFoundExceptionTest() {
        var result = productPhotoControllerExceptionHandler
                .handleProductNotFoundException(new ProductNotFoundException(1L));

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("No product was found with the given ID: #1.", result.getBody().error());
    }

    @Test
    public void handleInvalidFileExtensionExceptionTest() {
        var result = productPhotoControllerExceptionHandler
                .handleInvalidFileExtensionException(new InvalidFileExtensionException(JPEG));

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("Invalid file extension: .jpeg", result.getBody().error());
    }

    @Test
    public void handleProductPhotoUploadExceptionTest() {
        var result = productPhotoControllerExceptionHandler
                .handleProductPhotoUploadException(new ProductPhotoUploadException());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("The photo could not be uploaded successfully.", result.getBody().error());
    }

    @Test
    public void handleProductPhotoDeletionExceptionTest() {
        var result = productPhotoControllerExceptionHandler
                .handleProductPhotoDeletionException(new ProductPhotoDeletionException());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("The photo could not be deleted successfully.", result.getBody().error());
    }
}
