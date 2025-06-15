package com.github.jbence1994.webshop.image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductPhotoRepository extends JpaRepository<ProductPhoto, Long> {
    List<ProductPhoto> findAllByProductId(Long productId);
}
