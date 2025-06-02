package com.github.jbence1994.webshop.photo;

import java.io.IOException;
import java.io.InputStream;

public interface FileUtils {
    void store(String path, String fileName, InputStream stream) throws IOException;

    void remove(String path, String fileName) throws IOException;
}
