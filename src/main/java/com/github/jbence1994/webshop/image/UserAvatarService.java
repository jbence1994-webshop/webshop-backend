package com.github.jbence1994.webshop.image;

import com.github.jbence1994.webshop.user.AesCryptoService;
import com.github.jbence1994.webshop.user.UserQueryService;
import com.github.jbence1994.webshop.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAvatarService implements ImageService {
    private final FileExtensionValidator fileExtensionValidator;
    private final ImageUploadsConfig imageUploadsConfig;
    private final FileNameGenerator fileNameGenerator;
    private final UserQueryService userQueryService;
    private final AesCryptoService aesCryptoService;
    private final UserService userService;
    private final FileUtils fileUtils;

    @Override
    public String uploadImage(Long userId, ImageUpload image) {
        try {
            fileExtensionValidator.validate(image);

            var user = userQueryService.getEncryptedUser(userId);

            var avatarFileName = Optional.ofNullable(user.getAvatarFileName());

            avatarFileName.ifPresent(
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

            var encryptedFileName = aesCryptoService.encrypt(fileName);
            user.setAvatarFileName(encryptedFileName);

            userService.updateUser(user);

            return fileName;
        } catch (FileUploadException exception) {
            throw new ImageUploadException("The avatar could not be uploaded successfully.");
        }
    }

    @Override
    public void deleteImage(Long userId, String fileName) {
        try {
            var user = userQueryService.getEncryptedUser(userId);

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
