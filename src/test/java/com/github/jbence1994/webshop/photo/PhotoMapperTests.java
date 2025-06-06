package com.github.jbence1994.webshop.photo;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PhotoMapperTests {
    private final PhotoMapper mapper = Mappers.getMapper(PhotoMapper.class);
    private final MultipartFile multipartFile = mock(MultipartFile.class);

    @Test
    public void toDtoTest_HappyPath() throws IOException {
        var expectedBytes = new byte[]{1, 2, 3, 4, 5};
        when(multipartFile.getBytes()).thenReturn(expectedBytes);

        var result = mapper.toDto(multipartFile);

        assertArrayEquals(expectedBytes, result.getInputStreamBytes());

        verify(multipartFile, times(1)).getBytes();
    }

    @Test
    public void toDtoTest_UnhappyPath_IOException() throws IOException {
        when(multipartFile.getBytes()).thenThrow(new IOException("Disk error."));

        var result = assertThrows(
                ProductPhotoUploadException.class,
                () -> mapper.toDto(multipartFile)
        );

        assertEquals("The photo could not be uploaded successfully.", result.getMessage());
    }
}
