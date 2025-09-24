package com.github.jbence1994.webshop.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileExtensionValidatorImpl implements FileExtensionValidator {
    private final ImageUploadsConfig imageUploadsConfig;

    @Override
    public void validate(ImageUpload image) {
        var fileExtension = image.getFileExtension();

        if (!hasValidExtension(fileExtension)) {
            throw new InvalidFileExtensionException(fileExtension);
        }
    }

    private boolean hasValidExtension(String fileExtension) {
        return imageUploadsConfig.allowedFileExtensions()
                .stream()
                .anyMatch(extension -> extension.equals(fileExtension));
    }
}
