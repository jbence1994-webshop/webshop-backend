package com.github.jbence1994.webshop.checkout;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CheckoutRepository extends JpaRepository<CheckoutSession, UUID> {
}
