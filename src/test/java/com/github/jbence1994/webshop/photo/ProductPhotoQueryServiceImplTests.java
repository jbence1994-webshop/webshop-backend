package com.github.jbence1994.webshop.photo;

import com.github.jbence1994.webshop.product.ProductNotFoundException;
import com.github.jbence1994.webshop.product.ProductQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.github.jbence1994.webshop.photo.ProductPhotoTestObject.productPhoto;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductPhotoQueryServiceImplTests {

    @Mock
    private ProductQueryService productQueryService;

    @Mock
    private ProductPhotoRepository productPhotoRepository;

    @InjectMocks
    private ProductPhotoQueryServiceImpl productPhotoQueryService;

    @Test
    public void getProductPhotosTest_HappyPath() {
        when(productQueryService.isProductExistById(any())).thenReturn(true);
        when(productPhotoRepository.findAllByProductId(any())).thenReturn(List.of(productPhoto()));

        var result = productPhotoQueryService.getProductPhotos(1L);

        assertThat(result.size(), equalTo(1));
    }

    @Test
    public void getProductPhotosTest_UnhappyPath() {
        when(productQueryService.isProductExistById(any())).thenReturn(false);

        var result = assertThrows(
                ProductNotFoundException.class,
                () -> productPhotoQueryService.getProductPhotos(1L)
        );

        assertThat(result.getMessage(), equalTo("No product was found with the given ID: #1."));
    }
}
