package com.github.jbence1994.webshop.checkout;

import java.util.Map;

public record WebhookRequest(Map<String, String> headers, String payload) {
}
