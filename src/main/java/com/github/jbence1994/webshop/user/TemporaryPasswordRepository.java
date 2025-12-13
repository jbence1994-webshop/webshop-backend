package com.github.jbence1994.webshop.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TemporaryPasswordRepository extends JpaRepository<TemporaryPassword, Long> {
    List<TemporaryPassword> findAllByUserId(Long userId);

    Optional<TemporaryPassword> findTopByUserIdOrderByExpirationDateDesc(Long userId);
}
