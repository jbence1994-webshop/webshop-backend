package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.image.ImageUrlBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.image.ImageTestConstants.PHOTO_URL;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1WithPhotos;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhotoMapperTests {

    @Mock
    private ImageUrlBuilder imageUrlBuilder;

    @InjectMocks
    private PhotoMapper photoMapper;

    @Test
    public void toDtoTest_HappyPath() {
        when(imageUrlBuilder.buildUrl(any())).thenReturn(PHOTO_URL);

        var result = photoMapper.toDto(product1WithPhotos().getPhotos());

        assertThat(result, not(nullValue()));

    }

    @Test
    public void toDtoTest_UnhappyPath() {
        var result = photoMapper.toDto(product1().getPhotos());

        assertThat(result, is(nullValue()));
    }
}
