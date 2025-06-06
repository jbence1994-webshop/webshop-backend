package com.github.jbence1994.webshop.photo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FileExtensionValidatorImpl implements FileExtensionValidator {
    private final FileExtensionsConfig fileExtensionsConfig;

    @Override
    public void validate(UploadPhotoDto photo) {
        var fileExtension = photo.getFileExtension();

        if (!hasValidExtension(fileExtension)) {
            throw new InvalidFileExtensionException(fileExtension);
        }
    }

    private boolean hasValidExtension(String fileExtension) {
        return fileExtensionsConfig.getAllowedFileExtensions()
                .stream()
                .anyMatch(extension -> extension.equals(fileExtension));
    }
}
