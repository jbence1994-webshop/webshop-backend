package com.github.jbence1994.webshop.image;

import java.io.InputStream;

public interface FileUtils {
    void store(String path, String fileName, InputStream stream);

    void remove(String path, String fileName);
}
