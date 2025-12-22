package com.github.jbence1994.webshop.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecoveryCodeRepository extends JpaRepository<RecoveryCode, Long> {
    List<RecoveryCode> findAllByUserId(Long userId);

    Optional<RecoveryCode> findTopByUserIdOrderByExpirationDateDesc(Long userId);
}
