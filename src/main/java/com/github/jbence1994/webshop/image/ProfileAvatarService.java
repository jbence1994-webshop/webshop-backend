package com.github.jbence1994.webshop.image;

import com.github.jbence1994.webshop.user.UserQueryService;
import com.github.jbence1994.webshop.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileAvatarService implements ImageService {
    private final ProfileAvatarUploadDirectoryConfig profileAvatarUploadDirectoryConfig;
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

            if (user.hasProfileAvatar()) {
                fileUtils.remove(
                        profileAvatarUploadDirectoryConfig.getPath(),
                        user.getProfileAvatar()
                );
            }

            var fileName = fileNameGenerator.generate(image.getFileExtension());

            fileUtils.store(
                    profileAvatarUploadDirectoryConfig.getPath(),
                    fileName,
                    image.getInputStream()
            );

            user.setProfileAvatar(fileName);
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
                    profileAvatarUploadDirectoryConfig.getPath(),
                    fileName
            );

            user.setProfileAvatar(null);
            userService.updateUser(user);
        } catch (FileDeletionException exception) {
            throw new ImageDeletionException("The avatar could not be deleted successfully.");
        }
    }
}
