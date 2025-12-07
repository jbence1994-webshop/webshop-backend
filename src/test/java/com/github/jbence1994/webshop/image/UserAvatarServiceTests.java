package com.github.jbence1994.webshop.image;

import com.github.jbence1994.webshop.user.AesCryptoService;
import com.github.jbence1994.webshop.user.DecryptedUser;
import com.github.jbence1994.webshop.user.EncryptedUser;
import com.github.jbence1994.webshop.user.UserQueryService;
import com.github.jbence1994.webshop.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.ByteArrayInputStream;

import static com.github.jbence1994.webshop.image.ImageTestConstants.AVATAR_FILE_NAME;
import static com.github.jbence1994.webshop.image.ImageTestConstants.ENCRYPTED_AVATAR_FILE_NAME;
import static com.github.jbence1994.webshop.image.ImageTestConstants.FILE_SIZE;
import static com.github.jbence1994.webshop.image.ImageTestConstants.USER_AVATAR_DIRECTORY;
import static com.github.jbence1994.webshop.user.DecryptedUserTestObject.decryptedUser1WithoutAvatar;
import static com.github.jbence1994.webshop.user.EncryptedUserTestObject.encryptedUser1WithAvatar;
import static com.github.jbence1994.webshop.user.EncryptedUserTestObject.encryptedUser1WithoutAvatar;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserAvatarServiceTests {

    @Mock
    private FileExtensionValidator fileExtensionValidator;

    @Mock
    private ImageUploadsConfig imageUploadsConfig;

    @Mock
    private FileNameGenerator fileNameGenerator;

    @Mock
    private AesCryptoService aesCryptoService;

    @Mock
    private UserQueryService userQueryService;

    @Mock
    private UserService userService;

    @Mock
    private FileUtils fileUtils;

    @InjectMocks
    private UserAvatarService userAvatarService;

    private final EncryptedUser encryptedUserWithoutAvatar = spy(encryptedUser1WithoutAvatar());
    private final DecryptedUser decryptedUserWithoutAvatar = spy(decryptedUser1WithoutAvatar());
    private final EncryptedUser encryptedUserWithAvatar = spy(encryptedUser1WithAvatar());
    private final ImageUpload image = mock(ImageUpload.class);

    @BeforeEach
    public void setUp() {
        when(image.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[FILE_SIZE.intValue()]));
    }

    @Test
    public void uploadImageTest_HappyPath_UserDoNotHaveAvatarUploadedYet() {
        doNothing().when(fileExtensionValidator).validate(any());
        when(userQueryService.getEncryptedUser(anyLong())).thenReturn(encryptedUserWithoutAvatar);
        when(fileNameGenerator.generate(any())).thenReturn(AVATAR_FILE_NAME);
        when(imageUploadsConfig.userAvatarDirectory()).thenReturn(USER_AVATAR_DIRECTORY);
        doNothing().when(fileUtils).store(any(), any(), any());
        when(aesCryptoService.encrypt(any())).thenReturn(ENCRYPTED_AVATAR_FILE_NAME);
        doNothing().when(encryptedUserWithoutAvatar).setAvatarFileName(any());
        doNothing().when(userService).updateUser(any());

        var result = userAvatarService.uploadImage(1L, image);

        assertThat(result, equalTo(AVATAR_FILE_NAME));

        verify(fileExtensionValidator, times(1)).validate(any());
        verify(userQueryService, times(1)).getEncryptedUser(anyLong());
        verify(fileUtils, never()).remove(any(), any());
        verify(aesCryptoService, times(1)).encrypt(any());
        verify(fileNameGenerator, times(1)).generate(any());
        verify(imageUploadsConfig, times(1)).userAvatarDirectory();
        verify(image, times(1)).getInputStream();
        verify(fileUtils, times(1)).store(any(), any(), any());
        verify(encryptedUserWithoutAvatar, times(1)).setAvatarFileName(any());
        verify(userService, times(1)).updateUser(any());
    }

    @Test
    public void uploadImageTest_HappyPath_UserDoesHaveAvatarUploadedAlready() {
        doNothing().when(fileExtensionValidator).validate(any());
        when(userQueryService.getEncryptedUser(anyLong())).thenReturn(encryptedUserWithAvatar);
        when(fileNameGenerator.generate(any())).thenReturn(AVATAR_FILE_NAME);
        when(imageUploadsConfig.userAvatarDirectory()).thenReturn(USER_AVATAR_DIRECTORY);
        doNothing().when(fileUtils).remove(any(), any());
        doNothing().when(fileUtils).store(any(), any(), any());
        when(aesCryptoService.encrypt(any())).thenReturn(ENCRYPTED_AVATAR_FILE_NAME);
        doNothing().when(encryptedUserWithAvatar).setAvatarFileName(any());
        doNothing().when(userService).updateUser(any());

        var result = userAvatarService.uploadImage(1L, image);

        assertThat(result, equalTo(AVATAR_FILE_NAME));

        verify(fileExtensionValidator, times(1)).validate(any());
        verify(fileUtils, times(1)).remove(any(), any());
        verify(userQueryService, times(1)).getEncryptedUser(anyLong());
        verify(fileNameGenerator, times(1)).generate(any());
        verify(imageUploadsConfig, times(2)).userAvatarDirectory();
        verify(image, times(1)).getInputStream();
        verify(fileUtils, times(1)).store(any(), any(), any());
        verify(encryptedUserWithAvatar, times(1)).setAvatarFileName(any());
        verify(userService, times(1)).updateUser(any());
    }

    @Test
    public void uploadImageTest_UnhappyPath_ImageUploadException() {
        doNothing().when(fileExtensionValidator).validate(any());
        when(userQueryService.getEncryptedUser(anyLong())).thenReturn(encryptedUserWithoutAvatar);
        when(fileNameGenerator.generate(any())).thenReturn(AVATAR_FILE_NAME);
        when(imageUploadsConfig.userAvatarDirectory()).thenReturn(USER_AVATAR_DIRECTORY);
        doThrow(new FileUploadException("Disk error.")).when(fileUtils).store(any(), any(), any());

        var result = assertThrows(
                ImageUploadException.class,
                () -> userAvatarService.uploadImage(1L, image)
        );

        assertThat(result.getMessage(), equalTo("The avatar could not be uploaded successfully."));

        verify(fileExtensionValidator, times(1)).validate(any());
        verify(userQueryService, times(1)).getEncryptedUser(anyLong());
        verify(fileNameGenerator, times(1)).generate(any());
        verify(imageUploadsConfig, times(1)).userAvatarDirectory();
        verify(image, times(1)).getInputStream();
        verify(fileUtils, times(1)).store(any(), any(), any());
        verify(decryptedUserWithoutAvatar, never()).setAvatarFileName(any());
        verify(userService, never()).updateUser(any());
    }

    @Test
    public void deleteImageTest_HappyPath() {
        when(userQueryService.getEncryptedUser(anyLong())).thenReturn(encryptedUserWithAvatar);
        doNothing().when(fileUtils).remove(any(), any());
        doNothing().when(encryptedUserWithoutAvatar).setAvatarFileName(any());

        userAvatarService.deleteImage(1L, AVATAR_FILE_NAME);

        verify(fileUtils, times(1)).remove(any(), any());
        verify(encryptedUserWithAvatar, times(1)).setAvatarFileName(any());
        verify(userService, times(1)).updateUser(any());
    }

    @Test
    public void deleteImageTest_UnhappyPath_ImageDeletionException() {
        when(userQueryService.getEncryptedUser(anyLong())).thenReturn(encryptedUserWithAvatar);
        doThrow(new FileDeletionException("Disk error.")).when(fileUtils).remove(any(), any());

        var result = assertThrows(
                ImageDeletionException.class,
                () -> userAvatarService.deleteImage(1L, AVATAR_FILE_NAME)
        );

        assertThat(result.getMessage(), equalTo("The avatar could not be deleted successfully."));

        verify(fileUtils, times(1)).remove(any(), any());
        verify(decryptedUserWithoutAvatar, never()).setAvatarFileName(any());
        verify(userService, never()).updateUser(any());
    }
}
