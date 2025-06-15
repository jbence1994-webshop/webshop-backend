package com.github.jbence1994.webshop.image;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ImageMapperTests {
    private final ImageMapper imageMapper = Mappers.getMapper(ImageMapper.class);
    private final MultipartFile multipartFile = mock(MultipartFile.class);

    @Test
    public void toUploadImageTest_HappyPath() throws IOException {
        var expectedBytes = new byte[]{1, 2, 3, 4, 5};
        when(multipartFile.getBytes()).thenReturn(expectedBytes);

        var result = imageMapper.toUploadImage(multipartFile);

        assertThat(result.getInputStreamBytes(), is(equalTo(expectedBytes)));

        verify(multipartFile, times(1)).getBytes();
    }

    @Test
    public void toUploadImageTest_UnhappyPath_ImageUploadException() throws IOException {
        when(multipartFile.getBytes()).thenThrow(new IOException("Disk error."));

        var result = assertThrows(
                ImageUploadException.class,
                () -> imageMapper.toUploadImage(multipartFile)
        );

        assertThat(result.getMessage(), is(nullValue()));
    }
}
