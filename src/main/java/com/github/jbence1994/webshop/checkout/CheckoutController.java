package com.github.jbence1994.webshop.checkout;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {
    private final ApplyCouponToCheckoutSessionRequestSanitizer applyCouponToCheckoutSessionRequestSanitizer;
    private final CheckoutService checkoutService;
    private final CheckoutMapper checkoutMapper;

    @PostMapping("/create")
    public ResponseEntity<CheckoutSessionDto> createCheckoutSession(@Valid @RequestBody CreateCheckoutSessionRequest request) {
        var checkoutSession = checkoutService.createCheckoutSession(request.cartId());

        var checkoutSessionDto = checkoutMapper.toDto(checkoutSession);

        return ResponseEntity.status(HttpStatus.CREATED).body(checkoutSessionDto);
    }

    @PostMapping("/{id}/coupon")
    public CheckoutSessionDto applyCouponToCheckoutSession(
            @PathVariable UUID id,
            @Valid @RequestBody ApplyCouponToCheckoutSessionRequest request
    ) {
        var sanitizedRequest = applyCouponToCheckoutSessionRequestSanitizer.sanitize(request);

        var checkoutSession = checkoutService.applyCouponToCheckoutSession(id, sanitizedRequest.couponCode());

        return checkoutMapper.toDto(checkoutSession);
    }

    @DeleteMapping("/{id}/coupon")
    public CheckoutSessionDto removeCouponFromCheckoutSession(@PathVariable UUID id) {
        var checkoutSession = checkoutService.removeCouponFromCheckoutSession(id);

        return checkoutMapper.toDto(checkoutSession);
    }

    @PostMapping("/complete")
    public CheckoutSessionDto completeCheckoutSession(@Valid @RequestBody CompleteCheckoutSessionRequest request) {
        var checkoutSession = checkoutService.completeCheckoutSession(request.checkoutSessionId());

        return checkoutMapper.toDto(checkoutSession);
    }

    @PostMapping("/complete/webhook")
    public void handleCompleteCheckoutSessionWebhook(
            @RequestHeader Map<String, String> headers,
            @RequestBody String payload
    ) {
        checkoutService.handleCompleteCheckoutSessionWebhookEvent(new WebhookRequest(headers, payload));
    }
}
