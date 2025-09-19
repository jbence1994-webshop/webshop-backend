package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;

import static com.github.jbence1994.webshop.image.ImageTestConstants.PHOTO_FILE_NAME;
import static com.github.jbence1994.webshop.image.ImageTestConstants.PHOTO_NOT_EXISTING_FILE_NAME;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1WithPhotos;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ProductTests {
    private final Product product = product1();
    private final Product productWithPhotos = product1WithPhotos();

    @Test
    public void addPhotoTest() {
        product.addPhoto(PHOTO_FILE_NAME);

        assertThat(product.getPhotos().size(), equalTo(1));
    }

    @Test
    public void removePhotoTest_HappyPath_PhotoIsEmpty() {
        productWithPhotos.removePhoto(PHOTO_FILE_NAME);

        assertThat(productWithPhotos.getPhotos().isEmpty(), is(true));
    }

    @Test
    public void removePhotoTest_HappyPath_PhotoIsNotExists() {
        productWithPhotos.removePhoto(PHOTO_NOT_EXISTING_FILE_NAME);

        assertDoesNotThrow(() -> productWithPhotos.removePhoto(PHOTO_NOT_EXISTING_FILE_NAME));
    }

    @Test
    public void getFirstPhotoTest_HappyPath_PhotoIsPresent() {
        var result = productWithPhotos.getFirstPhoto();

        assertThat(result.isPresent(), is(true));
    }

    @Test
    public void getFirstPhotoTest_UnhappyPath_PhotoIsEmpty() {
        var result = product.getFirstPhoto();

        assertThat(result.isEmpty(), is(true));
    }
}
