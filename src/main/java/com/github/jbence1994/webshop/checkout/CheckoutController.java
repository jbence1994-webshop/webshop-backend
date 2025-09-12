package com.github.jbence1994.webshop.checkout;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping
    public CompleteCheckoutSessionResponse completeCheckoutSession(@Valid @RequestBody CompleteCheckoutSessionRequest request) {
        return checkoutService.completeCheckoutSession(request);
    }
}
