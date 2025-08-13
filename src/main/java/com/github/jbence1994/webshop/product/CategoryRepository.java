package com.github.jbence1994.webshop.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Byte> {
    List<Category> findAllByOrderByIdAsc();

    Optional<Category> findByName(String name);
}
