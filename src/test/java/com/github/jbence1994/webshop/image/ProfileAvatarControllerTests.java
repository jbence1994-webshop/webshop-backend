package com.github.jbence1994.webshop.image;

import com.github.jbence1994.webshop.user.UserQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static com.github.jbence1994.webshop.image.ImageTestConstants.AVATAR_URL;
import static com.github.jbence1994.webshop.image.ImageTestConstants.PHOTO_FILE_NAME;
import static com.github.jbence1994.webshop.image.ImageTestConstants.PHOTO_URL;
import static com.github.jbence1994.webshop.image.ImageUploadTestObject.jpegImageUpload;
import static com.github.jbence1994.webshop.image.MultipartFileTestObject.multipartFile;
import static com.github.jbence1994.webshop.user.UserTestObject.userWithAvatar;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfileAvatarControllerTests {

    @Mock
    private UserQueryService userQueryService;

    @Mock
    private ImageUrlBuilder imageUrlBuilder;

    @Mock
    private ImageService imageService;

    @Mock
    private ImageMapper imageMapper;

    @InjectMocks
    private ProfileAvatarController profileAvatarController;

    @Test
    public void uploadProfileAvatarTest() {
        when(imageMapper.toImageUpload(any())).thenReturn(jpegImageUpload());
        when(imageService.uploadImage(any(), any())).thenReturn(PHOTO_FILE_NAME);
        when(imageUrlBuilder.buildUrl(any())).thenReturn(PHOTO_URL);

        var result = profileAvatarController.uploadProfileAvatar(1L, multipartFile());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().fileName(), equalTo(PHOTO_FILE_NAME));
        assertThat(result.getBody().url(), equalTo(PHOTO_URL));
    }

    @Test
    public void getProfileAvatarTest() {
        when(userQueryService.getUser(anyLong())).thenReturn(userWithAvatar());
        when(imageUrlBuilder.buildUrl(any())).thenReturn(AVATAR_URL);

        var result = profileAvatarController.getProfileAvatar(1L);

        assertThat(result, not(nullValue()));
    }

    @Test
    public void deleteProfileAvatarTest() {
        doNothing().when(imageService).deleteImage(any(), any());

        var result = profileAvatarController.deleteProfileAvatar(1L, PHOTO_FILE_NAME);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
        assertThat(result.getBody(), is(nullValue()));
    }
}
