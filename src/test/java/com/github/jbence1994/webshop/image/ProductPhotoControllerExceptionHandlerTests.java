package com.github.jbence1994.webshop.image;

import com.github.jbence1994.webshop.product.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static com.github.jbence1994.webshop.image.PhotoTestConstants.JPEG;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@ExtendWith(MockitoExtension.class)
public class ProductPhotoControllerExceptionHandlerTests {

    @InjectMocks
    private ProductPhotoControllerExceptionHandler productPhotoControllerExceptionHandler;

    @Test
    public void handleProductNotFoundExceptionTest() {
        var result = productPhotoControllerExceptionHandler
                .handleProductNotFoundException(new ProductNotFoundException(1L));

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo("No product was found with the given ID: #1."));
    }

    @Test
    public void handleInvalidFileExtensionExceptionTest() {
        var result = productPhotoControllerExceptionHandler
                .handleInvalidFileExtensionException(new InvalidFileExtensionException(JPEG));

        assertThat(result.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo("Invalid file extension: .jpeg"));
    }

    @Test
    public void handleProductPhotoUploadExceptionTest() {
        var result = productPhotoControllerExceptionHandler
                .handleProductPhotoUploadException(new ProductPhotoUploadException());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo("The photo could not be uploaded successfully."));
    }

    @Test
    public void handleProductPhotoDeletionExceptionTest() {
        var result = productPhotoControllerExceptionHandler
                .handleProductPhotoDeletionException(new ProductPhotoDeletionException());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo("The photo could not be deleted successfully."));
    }
}
