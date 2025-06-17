package com.github.jbence1994.webshop.image;

import org.mapstruct.Context;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(target = "inputStreamBytes", expression = "java(getBytes(file))")
    ImageUpload toImageUpload(MultipartFile file);

    default byte[] getBytes(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException exception) {
            throw new ImageUploadException();
        }
    }

    @IterableMapping(qualifiedByName = "toImageResponse")
    List<ImageResponse> toImageResponses(List<ProductPhoto> photos, @Context ImageUrlBuilder imageUrlBuilder);

    @Named("toImageResponse")
    @Mapping(target = "url", expression = "java(imageUrlBuilder.buildUrl(photo.getFileName()))")
    ImageResponse toImageResponse(ProductPhoto photo, @Context ImageUrlBuilder imageUrlBuilder);
}
