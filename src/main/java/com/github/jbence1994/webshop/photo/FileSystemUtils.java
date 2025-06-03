package com.github.jbence1994.webshop.photo;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class FileSystemUtils implements FileUtils {

    @Override
    public void store(String path, String fileName, InputStream stream) {
        try {
            var targetPath = Paths.get(path);

            if (!Files.exists(targetPath)) {
                Files.createDirectories(targetPath);
            }

            Files.copy(stream, Paths.get(path, fileName));
        } catch (Exception exception) {
            throw new FileSystemException(exception.getMessage());
        }
    }

    @Override
    public void remove(String path, String fileName) {
        try {
            Files.delete(Paths.get(path, fileName));
        } catch (Exception exception) {
            throw new FileSystemException(exception.getMessage());
        }
    }
}
