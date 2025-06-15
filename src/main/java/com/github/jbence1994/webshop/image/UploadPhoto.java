package com.github.jbence1994.webshop.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UploadPhoto {
    private boolean isEmpty;
    private String originalFilename;
    private Long size;
    private String contentType;
    private byte[] inputStreamBytes;

    public InputStream getInputStream() {
        return new ByteArrayInputStream(inputStreamBytes);
    }

    public String getFileExtension() {
        return StringUtils.getFilenameExtension(originalFilename);
    }
}
