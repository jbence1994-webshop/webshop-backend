package com.github.jbence1994.webshop.photo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface PhotoMapper {

    @Mapping(target = "inputStreamBytes", expression = "java(getBytes(file))")
    UploadPhotoDto toDto(MultipartFile file);

    default byte[] getBytes(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException exception) {
            throw new ProductPhotoUploadException();
        }
    }
}
