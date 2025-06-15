package com.github.jbence1994.webshop.image;

import com.github.jbence1994.webshop.user.User;
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
import static com.github.jbence1994.webshop.image.ImageTestConstants.FILE_SIZE;
import static com.github.jbence1994.webshop.image.ImageTestConstants.PROFILE_AVATAR_UPLOAD_DIRECTORY_PATH;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
public class ProfileAvatarServiceTests {

    @Mock
    private ProfileAvatarUploadDirectoryConfig profileAvatarUploadDirectoryConfig;

    @Mock
    private UserQueryService userQueryService;

    @Mock
    private UserService userService;

    @Mock
    private FileExtensionValidator fileExtensionValidator;

    @Mock
    private FileNameGenerator fileNameGenerator;

    @Mock
    private FileUtils fileUtils;

    @InjectMocks
    private ProfileAvatarService profileAvatarService;

    private final User userWithoutAvatar = spy(user());
    private final UploadImage uploadImage = mock(UploadImage.class);

    @BeforeEach
    public void setUp() {
        doNothing().when(fileExtensionValidator).validate(any());
        when(userQueryService.getUser(any())).thenReturn(userWithoutAvatar);
        when(fileNameGenerator.generate(any())).thenReturn(AVATAR_FILE_NAME);
        when(profileAvatarUploadDirectoryConfig.getPath()).thenReturn(PROFILE_AVATAR_UPLOAD_DIRECTORY_PATH);
        when(uploadImage.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[FILE_SIZE.intValue()]));
        doNothing().when(userService).updateUser(any());
    }

    @Test
    public void uploadImageTest_HappyPath() {
        doNothing().when(fileUtils).store(any(), any(), any());
        doNothing().when(userWithoutAvatar).setProfileAvatar(any());

        var result = profileAvatarService.uploadImage(1L, uploadImage);

        assertThat(result, equalTo(AVATAR_FILE_NAME));

        verify(fileExtensionValidator, times(1)).validate(any());
        verify(userQueryService, times(1)).getUser(any());
        verify(fileNameGenerator, times(1)).generate(any());
        verify(profileAvatarUploadDirectoryConfig, times(1)).getPath();
        verify(uploadImage, times(1)).getInputStream();
        verify(fileUtils, times(1)).store(any(), any(), any());
        verify(userWithoutAvatar, times(1)).setProfileAvatar(any());
        verify(userService, times(1)).updateUser(any());
    }

    @Test
    public void uploadImageTest_UnhappyPath_ImageUploadException() {
        doThrow(new FileUploadException("Disk error.")).when(fileUtils).store(any(), any(), any());

        var result = assertThrows(
                ImageUploadException.class,
                () -> profileAvatarService.uploadImage(1L, uploadImage)
        );

        assertThat(result.getMessage(), equalTo("The avatar could not be uploaded successfully."));

        verify(fileExtensionValidator, times(1)).validate(any());
        verify(userQueryService, times(1)).getUser(any());
        verify(fileNameGenerator, times(1)).generate(any());
        verify(profileAvatarUploadDirectoryConfig, times(1)).getPath();
        verify(uploadImage, times(1)).getInputStream();
        verify(fileUtils, times(1)).store(any(), any(), any());
        verify(userWithoutAvatar, never()).setProfileAvatar(any());
        verify(userService, never()).updateUser(any());
    }

    @Test
    public void deleteImageTest_HappyPath() {
        doNothing().when(fileUtils).remove(any(), any());
        doNothing().when(userWithoutAvatar).setProfileAvatar(any());

        profileAvatarService.deleteImage(1L, AVATAR_FILE_NAME);

        verify(fileUtils, times(1)).remove(any(), any());
        verify(userWithoutAvatar, times(1)).setProfileAvatar(any());
        verify(userService, times(1)).updateUser(any());
    }

    @Test
    public void deleteImageTest_UnhappyPath_ImageDeletionException() {
        doThrow(new FileDeletionException("Disk error.")).when(fileUtils).remove(any(), any());

        var result = assertThrows(
                ImageDeletionException.class,
                () -> profileAvatarService.deleteImage(1L, AVATAR_FILE_NAME)
        );

        assertThat(result.getMessage(), equalTo("The avatar could not be deleted successfully."));

        verify(fileUtils, times(1)).remove(any(), any());
        verify(userWithoutAvatar, never()).setProfileAvatar(any());
        verify(userService, never()).updateUser(any());
    }
}
