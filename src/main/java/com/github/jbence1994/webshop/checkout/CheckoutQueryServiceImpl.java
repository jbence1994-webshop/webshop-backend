package com.github.jbence1994.webshop.checkout;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutQueryServiceImpl implements CheckoutQueryService {
    private final CheckoutRepository checkoutRepository;

    @Override
    public CheckoutSession getCheckoutSession(UUID id) {
        return checkoutRepository
                .findById(id)
                .orElseThrow(() -> new CheckoutSessionNotFoundException(id));
    }

    @Override
    public boolean existsByCartId(UUID cartId) {
        return checkoutRepository.existsByCartId(cartId);
    }
}
