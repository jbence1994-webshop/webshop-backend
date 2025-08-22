package com.github.jbence1994.webshop.common;

public interface InputSanitizer<T> {
    T sanitize(T input);
}
