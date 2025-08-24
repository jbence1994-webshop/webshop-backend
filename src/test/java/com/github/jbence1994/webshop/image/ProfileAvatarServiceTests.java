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
import static com.github.jbence1994.webshop.image.ImageTestConstants.PROFILE_AVATAR_DIRECTORY;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static com.github.jbence1994.webshop.user.UserTestObject.userWithAvatar;
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
public class ProfileAvatarServiceTests {

    @Mock
    private ImageUploadsConfig imageUploadsConfig;

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
    private final User user = spy(userWithAvatar());
    private final ImageUpload image = mock(ImageUpload.class);

    @BeforeEach
    public void setUp() {
        doNothing().when(fileExtensionValidator).validate(any());
        when(userQueryService.getUser(anyLong())).thenReturn(userWithoutAvatar);
        when(fileNameGenerator.generate(any())).thenReturn(AVATAR_FILE_NAME);
        when(imageUploadsConfig.profileAvatarDirectory()).thenReturn(PROFILE_AVATAR_DIRECTORY);
        when(image.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[FILE_SIZE.intValue()]));
        doNothing().when(userService).updateUser(any());
    }

    @Test
    public void uploadImageTest_HappyPath_UserDoNotHaveAvatarUploadedYet() {
        when(userWithoutAvatar.hasProfileAvatar()).thenReturn(false);
        doNothing().when(fileUtils).store(any(), any(), any());
        doNothing().when(userWithoutAvatar).setProfileAvatar(any());

        var result = profileAvatarService.uploadImage(1L, image);

        assertThat(result, equalTo(AVATAR_FILE_NAME));

        verify(fileExtensionValidator, times(1)).validate(any());
        verify(userQueryService, times(1)).getUser(anyLong());
        verify(userWithoutAvatar, times(1)).hasProfileAvatar();
        verify(fileUtils, never()).remove(any(), any());
        verify(fileNameGenerator, times(1)).generate(any());
        verify(imageUploadsConfig, times(1)).profileAvatarDirectory();
        verify(image, times(1)).getInputStream();
        verify(fileUtils, times(1)).store(any(), any(), any());
        verify(userWithoutAvatar, times(1)).setProfileAvatar(any());
        verify(userService, times(1)).updateUser(any());
    }

    @Test
    public void uploadImageTest_HappyPath_UserDoesHaveAvatarUploadedAlready() {
        when(userQueryService.getUser(anyLong())).thenReturn(user);
        when(user.hasProfileAvatar()).thenReturn(true);
        doNothing().when(fileUtils).remove(any(), any());
        doNothing().when(fileUtils).store(any(), any(), any());
        doNothing().when(userWithoutAvatar).setProfileAvatar(any());

        var result = profileAvatarService.uploadImage(1L, image);

        assertThat(result, equalTo(AVATAR_FILE_NAME));

        verify(fileExtensionValidator, times(1)).validate(any());
        verify(fileUtils, times(1)).remove(any(), any());
        verify(userQueryService, times(1)).getUser(anyLong());
        verify(user, times(1)).hasProfileAvatar();
        verify(fileNameGenerator, times(1)).generate(any());
        verify(imageUploadsConfig, times(2)).profileAvatarDirectory();
        verify(image, times(1)).getInputStream();
        verify(fileUtils, times(1)).store(any(), any(), any());
        verify(user, times(1)).setProfileAvatar(any());
        verify(userService, times(1)).updateUser(any());
    }

    @Test
    public void uploadImageTest_UnhappyPath_ImageUploadException() {
        doThrow(new FileUploadException("Disk error.")).when(fileUtils).store(any(), any(), any());

        var result = assertThrows(
                ImageUploadException.class,
                () -> profileAvatarService.uploadImage(1L, image)
        );

        assertThat(result.getMessage(), equalTo("The avatar could not be uploaded successfully."));

        verify(fileExtensionValidator, times(1)).validate(any());
        verify(userQueryService, times(1)).getUser(anyLong());
        verify(fileNameGenerator, times(1)).generate(any());
        verify(imageUploadsConfig, times(1)).profileAvatarDirectory();
        verify(image, times(1)).getInputStream();
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
