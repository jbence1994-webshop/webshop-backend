package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.image.ImageUrlBuilder;
import com.github.jbence1994.webshop.image.ProductPhoto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "photo", ignore = true)
    @Mapping(target = "averageRating", expression = "java(product.calculateAverageRating())")
    ProductDto toProductDto(Product product);

    @Mapping(target = "url", expression = "java(imageUrlBuilder.buildUrl(productPhoto.getFileName()))")
    ProductPhotoDto toProductPhotoDto(ProductPhoto productPhoto, @Context ImageUrlBuilder imageUrlBuilder);

    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "photo", ignore = true)
    @Mapping(target = "averageRating", expression = "java(product.calculateAverageRating())")
    ProductByIdDto toProductByIdDto(Product product);

    WishlistProductDto toWishlistProductDto(Product product);

    @Mapping(
            target = "name",
            expression = """
                    java(
                    productReview.getUser().getFirstName() + " " +
                    productReview.getUser().getMiddleName() != null ?
                    productReview.getUser().getMiddleName() : "" +
                    " " +
                    productReview.getUser().getLastName()
                    )
                    """
    )
    ProductReviewDto toProductReviewDto(ProductReview productReview);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "yourRating", source = "request.ratingValue")
    @Mapping(target = "averageRating", expression = "java(product.calculateAverageRating())")
    @Mapping(target = "totalRatings", expression = "java(product.getRatings().size())")
    ProductRatingResponse toProductRatingResponse(CreateProductRatingRequest request, Product product);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "yourRating", source = "request.ratingValue")
    @Mapping(target = "averageRating", expression = "java(product.calculateAverageRating())")
    @Mapping(target = "totalRatings", expression = "java(product.getRatings().size())")
    ProductRatingResponse toProductRatingResponse(UpdateProductRatingRequest request, Product product);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "review", source = "request.review")
    ProductReviewResponse toProductReviewResponse(CreateProductReviewRequest request, Product product);

    @Mapping(target = "text", source = "productReviewSummary.text")
    ProductReviewSummaryResponse toProductReviewSummaryResponse(ProductReviewSummary productReviewSummary);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photos", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "ratings", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    Product toProduct(ProductDto productDto);
}
