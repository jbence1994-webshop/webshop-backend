package com.github.jbence1994.webshop.image;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static com.github.jbence1994.webshop.image.ImageResponseTestObject.imageResponse;
import static com.github.jbence1994.webshop.image.ImageTestConstants.PHOTO_FILE_NAME;
import static com.github.jbence1994.webshop.image.ImageTestConstants.PHOTO_URL;
import static com.github.jbence1994.webshop.image.MultipartFileTestObject.multipartFile;
import static com.github.jbence1994.webshop.image.ProductPhotoTestObject.productPhoto;
import static com.github.jbence1994.webshop.image.UploadImageTestObject.jpegUploadImage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductPhotoControllerTests {

    @Mock
    private ProductPhotoQueryService productPhotoQueryService;

    @Mock
    private ImageService imageService;

    @Mock
    private ImageMapper imageMapper;

    @Mock
    private ImageUrlBuilder imageUrlBuilder;

    @InjectMocks
    private ProductPhotoController productPhotoController;

    @Test
    public void uploadProductPhotoTest() {
        when(imageMapper.toUploadImage(any())).thenReturn(jpegUploadImage());
        when(imageService.uploadImage(any(), any())).thenReturn(PHOTO_FILE_NAME);
        when(imageUrlBuilder.buildUrl(any())).thenReturn(PHOTO_URL);

        var result = productPhotoController.uploadProductPhoto(1L, multipartFile());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().fileName(), equalTo(PHOTO_FILE_NAME));
        assertThat(result.getBody().url(), equalTo(PHOTO_URL));
    }

    @Test
    public void getProductPhotosTest() {
        when(productPhotoQueryService.getProductPhotos(any())).thenReturn(List.of(productPhoto()));
        when(imageMapper.toImageResponses(any(), any())).thenReturn(List.of(imageResponse()));

        var result = productPhotoController.getProductPhotos(1L);

        assertThat(result.size(), equalTo(1));
    }

    @Test
    public void deleteProductPhotoTest() {
        doNothing().when(imageService).deleteImage(any(), any());

        var result = productPhotoController.deleteProductPhoto(1L, PHOTO_FILE_NAME);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
        assertThat(result.getBody(), is(nullValue()));
    }
}
