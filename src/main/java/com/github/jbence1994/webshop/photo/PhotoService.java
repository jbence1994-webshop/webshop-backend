package com.github.jbence1994.webshop.photo;

import java.util.List;

public interface PhotoService {
    DownloadPhotoDto uploadPhoto(Long id, UploadPhotoDto uploadPhotoDto);

    List<DownloadPhotoDto> getPhotos(Long id);

    void deletePhoto(Long id, String fileName);
}
