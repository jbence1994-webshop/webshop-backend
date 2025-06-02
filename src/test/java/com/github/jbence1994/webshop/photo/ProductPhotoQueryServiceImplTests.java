package com.github.jbence1994.webshop.photo;

import com.github.jbence1994.webshop.product.ProductNotFoundException;
import com.github.jbence1994.webshop.product.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.github.jbence1994.webshop.photo.ProductPhotoTestObject.productPhoto1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductPhotoQueryServiceImplTests {

    @Mock
    private ProductService productService;

    @Mock
    private ProductPhotoRepository productPhotoRepository;

    @InjectMocks
    private ProductPhotoQueryServiceImpl productPhotoQueryService;

    @Test
    public void getProductPhotosTest_HappyPath() {
        when(productService.isProductExistById(any())).thenReturn(true);
        when(productPhotoRepository.findAllByProductId(any())).thenReturn(List.of(productPhoto1()));

        var result = productPhotoQueryService.getProductPhotos(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void getProductPhotosTest_UnhappyPath() {
        when(productService.isProductExistById(any())).thenReturn(false);

        assertThrows(ProductNotFoundException.class, () -> productPhotoQueryService.getProductPhotos(1L));
    }
}
