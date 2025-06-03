package com.github.jbence1994.webshop.photo;

import com.github.jbence1994.webshop.product.Product;
import com.github.jbence1994.webshop.product.ProductQueryService;
import com.github.jbence1994.webshop.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static com.github.jbence1994.webshop.photo.PhotoTestConstants.FILE_SIZE;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.PHOTO_FILE_NAME;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.PRODUCT_PHOTOS_UPLOAD_DIRECTORY_PATH;
import static com.github.jbence1994.webshop.photo.ProductPhotoTestObject.productPhoto;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductPhotoServiceImplTests {

    @Mock
    private FileExtensionValidator fileExtensionValidator;

    @Mock
    private ProductPhotosUploadDirectoryPathConfig productPhotosUploadDirectoryPathConfig;

    @Mock
    private ProductQueryService productQueryService;

    @Mock
    private ProductService productService;

    @Mock
    private ProductPhotoQueryService productPhotoQueryService;

    @Mock
    private FileUtils fileUtils;

    @InjectMocks
    private ProductPhotoServiceImpl productPhotoService;

    private final Product product = spy(product1());
    private final Photo photo = mock(Photo.class);

    @BeforeEach
    public void setUp() {
        doNothing().when(fileExtensionValidator).validate(any());
        when(productQueryService.getProduct(any())).thenReturn(product);
        when(photo.generateFileName()).thenReturn(PHOTO_FILE_NAME);
        when(productPhotosUploadDirectoryPathConfig.getPath()).thenReturn(PRODUCT_PHOTOS_UPLOAD_DIRECTORY_PATH);
        when(photo.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[FILE_SIZE.intValue()]));
        doNothing().when(productService).updateProduct(any());

    }

    @Test
    public void uploadProductPhotoTest_HappyPath() throws IOException {
        doNothing().when(fileUtils).store(any(), any(), any());
        doNothing().when(product).addPhoto(any());

        var result = productPhotoService.uploadProductPhoto(1L, photo);

        assertEquals(PHOTO_FILE_NAME, result);

        verify(fileExtensionValidator, times(1)).validate(any());
        verify(productQueryService, times(1)).getProduct(any());
        verify(photo, times(1)).generateFileName();
        verify(productPhotosUploadDirectoryPathConfig, times(1)).getPath();
        verify(photo, times(1)).getInputStream();
        verify(fileUtils, times(1)).store(any(), any(), any());
        verify(product, times(1)).addPhoto(any());
        verify(productService, times(1)).updateProduct(any());
    }

    @Test
    public void uploadProductPhotoTest_UnhappyPath_IOException() throws IOException {
        doThrow(new IOException("Disk error.")).when(fileUtils).store(any(), any(), any());

        var result = assertThrows(
                ProductPhotoUploadException.class,
                () -> productPhotoService.uploadProductPhoto(1L, photo)
        );

        assertEquals("The photo could not be uploaded successfully.", result.getMessage());

        verify(fileExtensionValidator, times(1)).validate(any());
        verify(productQueryService, times(1)).getProduct(any());
        verify(photo, times(1)).generateFileName();
        verify(productPhotosUploadDirectoryPathConfig, times(1)).getPath();
        verify(photo, times(1)).getInputStream();
        verify(fileUtils, times(1)).store(any(), any(), any());
        verify(product, never()).addPhoto(any());
        verify(productService, never()).updateProduct(any());
    }

    @Test
    public void getProductPhotosTest_HappyPath() {
        when(productPhotoQueryService.getProductPhotos(any())).thenReturn(List.of(productPhoto()));

        var result = productPhotoService.getProductPhotos(1L);

        assertEquals(1, result.size());
    }

    @Test
    public void deleteProductPhotoTest_HappyPath() throws IOException {
        doNothing().when(fileUtils).remove(any(), any());
        doNothing().when(product).removePhoto(any());

        productPhotoService.deleteProductPhoto(1L, PHOTO_FILE_NAME);

        verify(fileUtils, times(1)).remove(any(), any());
        verify(product, times(1)).removePhoto(any());
        verify(productService, times(1)).updateProduct(any());
    }

    @Test
    public void deleteProductPhotoTest_UnhappyPath_IOException() throws IOException {
        doThrow(new IOException("Disk error.")).when(fileUtils).remove(any(), any());

        var result = assertThrows(
                ProductPhotoDeletionException.class,
                () -> productPhotoService.deleteProductPhoto(1L, PHOTO_FILE_NAME)
        );

        assertEquals("The photo could not be deleted successfully.", result.getMessage());

        verify(fileUtils, times(1)).remove(any(), any());
        verify(product, never()).removePhoto(any());
        verify(productService, never()).updateProduct(any());
    }
}
