package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.image.ImageUrlBuilder;
import com.github.jbence1994.webshop.image.ProductPhoto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    CategoryDto toCategoryDto(Category category);

    @Mapping(target = "url", expression = "java(imageUrlBuilder.buildUrl(productPhoto.getFileName()))")
    ProductPhotoDto toProductPhotoDto(ProductPhoto productPhoto, @Context ImageUrlBuilder imageUrlBuilder);

    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "photo", ignore = true)
    @Mapping(target = "averageRating", expression = "java(product.calculateAverageRating())")
    ProductDto toProductDto(Product product);

    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "photo", ignore = true)
    @Mapping(target = "averageRating", expression = "java(product.calculateAverageRating())")
    ProductByIdDto toProductByIdDto(Product product);

    WishlistProductDto toWishlistProductDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photos", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "ratings", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    Product toEntity(ProductDto productDto);

    @Mapping(
            target = "name",
            expression = """
                    java(
                    productReview.getProfile().getFirstName() + " " +
                    (productReview.getProfile().getMiddleName() != null ? productReview.getProfile().getMiddleName() + " " : "") +
                    productReview.getProfile().getLastName()
                    )
                    """
    )
    ProductReviewDto toProductReviewDto(ProductReview productReview);
}
