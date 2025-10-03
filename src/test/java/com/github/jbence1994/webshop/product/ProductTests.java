package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.image.ImageTestConstants.PHOTO_FILE_NAME;
import static com.github.jbence1994.webshop.image.ImageTestConstants.PHOTO_NOT_EXISTING_FILE_NAME;
import static com.github.jbence1994.webshop.product.ProductRatingTestObject.productRating;
import static com.github.jbence1994.webshop.product.ProductReviewTestObject.productReview;
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

    private static Stream<Arguments> ratingIdParams() {
        return Stream.of(
                Arguments.of("Rating ID: 1", 1L),
                Arguments.of("Rating ID: 2", 2L)
        );
    }

    @Test
    public void addPhotoTest() {
        product.addPhoto(PHOTO_FILE_NAME);

        assertThat(product.getPhotos().size(), equalTo(1));
    }

    @Test
    public void removePhotoTest_HappyPath_PhotoIsExists() {
        productWithPhotos.removePhoto(PHOTO_FILE_NAME);

        assertThat(productWithPhotos.getPhotos(), is(empty()));
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

    @Test
    public void addRatingTest() {
        product.addRating(productRating((byte) 1));
        product.addRating(productRating((byte) 2));
        product.addRating(productRating((byte) 3));
        product.addRating(productRating((byte) 4));
        product.addRating(productRating((byte) 5));

        assertThat(product.getRatings().size(), equalTo(5));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("ratingIdParams")
    public void updateRatingTest(
            String testCase,
            Long ratingId
    ) {
        product.addRating(productRating((byte) 1));

        assertDoesNotThrow(() -> product.updateRating(ratingId, (byte) 1));

        assertThat(product.getRatings().size(), equalTo(1));
    }

    @Test
    public void calculateAverageRatingTest() {
        product.addRating(productRating((byte) 1));
        product.addRating(productRating((byte) 2));
        product.addRating(productRating((byte) 3));
        product.addRating(productRating((byte) 4));
        product.addRating(productRating((byte) 5));

        var result = product.calculateAverageRating();

        assertThat(result, equalTo(3.0));
    }

    @Test
    public void addReviewTest() {
        product.addReview(productReview());
        product.addReview(productReview());

        assertThat(product.getReviews().size(), equalTo(2));
    }
}
