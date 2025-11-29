package com.github.jbence1994.webshop.checkout;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckoutQueryServiceImpl implements CheckoutQueryService {
    private final CheckoutRepository checkoutRepository;

    @Override
    public CheckoutSession getCheckoutSession(UUID id) {
        return checkoutRepository
                .findById(id)
                .orElseThrow(() -> new CheckoutSessionNotFoundException(id));
    }
}
