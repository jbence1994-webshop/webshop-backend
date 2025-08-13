package com.github.jbence1994.webshop.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Byte> {
    List<Category> findAllByOrderByIdAsc();
}
