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
public interface PhotoMapper {

    @Mapping(target = "inputStreamBytes", expression = "java(getBytes(file))")
    UploadPhoto toUploadPhoto(MultipartFile file);

    default byte[] getBytes(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException exception) {
            throw new ProductPhotoUploadException();
        }
    }

    @IterableMapping(qualifiedByName = "toPhotoResponse")
    List<PhotoResponse> toPhotoResponses(List<ProductPhoto> photos, @Context PhotoUrlBuilder photoUrlBuilder);

    @Named("toPhotoResponse")
    @Mapping(target = "url", expression = "java(photoUrlBuilder.buildUrl(photo.getFileName()))")
    PhotoResponse toPhotoResponse(ProductPhoto photo, @Context PhotoUrlBuilder photoUrlBuilder);
}
