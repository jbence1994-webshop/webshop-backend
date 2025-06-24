package com.github.jbence1994.webshop.image;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Data
public class ImageUpload {
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
