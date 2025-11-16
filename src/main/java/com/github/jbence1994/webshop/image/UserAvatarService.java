package com.github.jbence1994.webshop.image;

import com.github.jbence1994.webshop.user.UserQueryService;
import com.github.jbence1994.webshop.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAvatarService implements ImageService {
    private final ImageUploadsConfig imageUploadsConfig;
    private final UserQueryService userQueryService;
    private final UserService userService;
    private final FileExtensionValidator fileExtensionValidator;
    private final FileNameGenerator fileNameGenerator;
    private final FileUtils fileUtils;

    @Override
    public String uploadImage(Long userId, ImageUpload image) {
        try {
            fileExtensionValidator.validate(image);

            var user = userQueryService.getUser(userId);

            user.getAvatarFileName()
                    .ifPresent(
                            userAvatarFileName -> fileUtils.remove(
                                    imageUploadsConfig.userAvatarDirectory(),
                                    userAvatarFileName
                            )
                    );

            var fileName = fileNameGenerator.generate(image.getFileExtension());

            fileUtils.store(
                    imageUploadsConfig.userAvatarDirectory(),
                    fileName,
                    image.getInputStream()
            );

            user.setAvatarFileName(fileName);
            userService.updateUser(user);

            return fileName;
        } catch (FileUploadException exception) {
            throw new ImageUploadException("The avatar could not be uploaded successfully.");
        }
    }

    @Override
    public void deleteImage(Long userId, String fileName) {
        try {
            var user = userQueryService.getUser(userId);

            fileUtils.remove(
                    imageUploadsConfig.userAvatarDirectory(),
                    fileName
            );

            user.setAvatarFileName(null);
            userService.updateUser(user);
        } catch (FileDeletionException exception) {
            throw new ImageDeletionException("The avatar could not be deleted successfully.");
        }
    }
}
