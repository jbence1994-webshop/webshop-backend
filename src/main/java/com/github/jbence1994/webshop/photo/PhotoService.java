package com.github.jbence1994.webshop.photo;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public abstract class PhotoService<Entity, PhotoEntity extends Photo> {
    private final FileExtensionValidator fileExtensionValidator;
    private final FileNameGenerator fileNameGenerator;
    private final FileUtils fileUtils;

    protected abstract String getPhotosUploadDirectoryPath();

    protected abstract Entity getEntity(Long entityId);

    protected abstract void updateEntity(Entity entity);

    protected abstract void addPhotoToEntity(Entity entity, String fileName);

    protected abstract void removePhotoFromEntity(Entity entity, String fileName);

    protected abstract List<PhotoEntity> getEntityPhotos(Long entityId);

    protected abstract RuntimeException photoUploadException();

    protected abstract RuntimeException photoDeletionException();

    public DownloadPhotoDto uploadPhoto(Long entityId, UploadPhotoDto uploadPhotoDto) {
        try {
            fileExtensionValidator.validate(uploadPhotoDto);

            var entity = getEntity(entityId);

            var fileName = fileNameGenerator.generate(uploadPhotoDto.getFileExtension());

            fileUtils.store(
                    getPhotosUploadDirectoryPath(),
                    fileName,
                    uploadPhotoDto.getInputStream()
            );

            addPhotoToEntity(entity, fileName);
            updateEntity(entity);

            return new DownloadPhotoDto(fileName);
        } catch (FileUploadException exception) {
            throw photoUploadException();
        }
    }

    public List<DownloadPhotoDto> getPhotos(Long entityId) {
        return getEntityPhotos(entityId).stream()
                .map(photoEntity -> new DownloadPhotoDto(photoEntity.getFileName()))
                .toList();
    }

    public void deletePhoto(Long entityId, String fileName) {
        try {
            var entity = getEntity(entityId);

            fileUtils.remove(
                    getPhotosUploadDirectoryPath(),
                    fileName
            );

            removePhotoFromEntity(entity, fileName);
            updateEntity(entity);
        } catch (FileDeletionException exception) {
            throw photoDeletionException();
        }
    }
}
