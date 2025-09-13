package com.github.jbence1994.webshop.checkout;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {
    private final CheckoutService checkoutService;
    private final CheckoutMapper checkoutMapper;

    @PostMapping("/create")
    public ResponseEntity<CheckoutSessionDto> createCheckoutSession(@Valid @RequestBody CreateCheckoutSessionRequest request) {
        var checkoutSession = checkoutService.createCheckoutSession(request.getCartId());

        var checkoutSessionDto = checkoutMapper.toDto(checkoutSession);

        return ResponseEntity.status(HttpStatus.CREATED).body(checkoutSessionDto);
    }

    @PostMapping("/complete")
    public CompleteCheckoutSessionResponse completeCheckoutSession(@Valid @RequestBody CompleteCheckoutSessionRequest request) {
        return checkoutService.completeCheckoutSession(request.getCheckoutSessionId());
    }
}
