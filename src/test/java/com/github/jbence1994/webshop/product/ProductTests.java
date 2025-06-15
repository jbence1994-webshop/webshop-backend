package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;

import static com.github.jbence1994.webshop.image.PhotoTestConstants.PHOTO_FILE_NAME;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.PHOTO_NOT_EXISTING_FILE_NAME;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1WithPhotos;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
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
    public void removePhotoTest_HappyPath_PhotoIsNotNull() {
        productWithPhotos.removePhoto(PHOTO_FILE_NAME);

        assertThat(productWithPhotos.getPhotos(), is(empty()));
    }

    @Test
    public void removePhotoTest_HappyPath_PhotoIsNull() {
        productWithPhotos.removePhoto(PHOTO_NOT_EXISTING_FILE_NAME);

        assertDoesNotThrow(() -> productWithPhotos.removePhoto(PHOTO_NOT_EXISTING_FILE_NAME));
    }
}
