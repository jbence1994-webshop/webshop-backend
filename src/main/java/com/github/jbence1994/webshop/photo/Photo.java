package com.github.jbence1994.webshop.photo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Photo {
    private boolean isEmpty;
    private String originalFilename;
    private Long size;
    private String contentType;
    private byte[] inputStreamBytes;

    public InputStream getInputStream() {
        return new ByteArrayInputStream(inputStreamBytes);
    }

    public String generateFileName() {
        return String.format("%s.%s", UUID.randomUUID(), getFileExtension());
    }

    public String getFileExtension() {
        return StringUtils.getFilenameExtension(originalFilename);
    }
}
