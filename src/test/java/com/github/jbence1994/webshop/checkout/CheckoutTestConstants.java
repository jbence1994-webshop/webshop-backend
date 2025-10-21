package com.github.jbence1994.webshop.checkout;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface CheckoutTestConstants {
    UUID CHECKOUT_SESSION_ID = UUID.fromString("401c3a9e-c1ae-4a39-956b-9af3ed28a4e2");
    LocalDateTime CREATED_AT = LocalDateTime.of(2025, 9, 13, 11, 11, 11);
    LocalDateTime NOT_EXPIRED_CHECKOUT_SESSION_EXPIRATION_DATE = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
    LocalDateTime EXPIRED_CHECKOUT_SESSION_EXPIRATION_DATE = LocalDateTime.of(2025, 9, 14, 23, 59, 59);
    BigDecimal FREE_SHIPPING_THRESHOLD = BigDecimal.valueOf(199.99);
    String CHECKOUT_URL = "https://checkout.stripe.com/c/pay/cs_test_a105cJGCzEbthAbIntqGMD3jo6aVag4qYg28WOfm53ru2qvnLLqpfPGB3K#fidnandhYHdWcXxpYCc%2FJ2FgY2RwaXEnKSdkdWxOYHwnPyd1blpxYHZxWjA0VjFCbkhHQ21JTnZcPH1Cd25qbj01az13TFZuZ2R1aUMwSVJDczZyfXxIPXxdQzBPVkk0Mk5SSXNIYUpINDZ2ZEhjMWdnUDU3RlxwSE5tPGdrMmgwcnE3NTVJaFY8RkQ2aicpJ2N3amhWYHdzYHcnP3F3cGApJ2dkZm5id2pwa2FGamlqdyc%2FJyZjY2NjY2MnKSdpZHxqcHFRfHVgJz8ndmxrYmlgWmxxYGgnKSdga2RnaWBVaWRmYG1qaWFgd3YnP3F3cGB4JSUl";
    String STRIPE_SIGNATURE = "t=1760877524,v1=d765774c33b5b6bbdab7fd8daef3449ac913f9632e06b7e6418d784259f2e35a,v0=07d4aa25a85a7fdbc800736f290c823f6026f2b723b6849969ba7b93073872df";
    String STRIPE_PAYLOAD = """
            {
              "id": "evt_3SKfMTBFhLKsY9xG0nzvhjw9",
              "object": "event",
              "api_version": "2025-08-27.basil",
              "created": 1761052346,
              "data": {
                "object": {
                  "id": "pi_3SKfMTBFhLKsY9xG0GfbIpNO",
                  "object": "payment_intent",
                  "amount": 5798,
                  "amount_capturable": 0,
                  "amount_details": {
                    "shipping": {
                      "amount": 799,
                      "from_postal_code": null,
                      "to_postal_code": null
                    },
                    "tax": {
                      "total_tax_amount": 0
                    },
                    "tip": {}
                  },
                  "amount_received": 5798,
                  "application": null,
                  "application_fee_amount": null,
                  "automatic_payment_methods": null,
                  "canceled_at": null,
                  "cancellation_reason": null,
                  "capture_method": "automatic_async",
                  "client_secret": "pi_3SKfMTBFhLKsY9xG0GfbIpNO_secret_9mc36MNlYLtBhiiMG8jT28FEu",
                  "confirmation_method": "automatic",
                  "created": 1761052345,
                  "currency": "usd",
                  "customer": null,
                  "description": null,
                  "excluded_payment_method_types": null,
                  "last_payment_error": null,
                  "latest_charge": "ch_3SKfMTBFhLKsY9xG0pXaA1f4",
                  "livemode": false,
                  "metadata": {
                    "cart_id": "6af3af56-7230-4134-b36b-63b261a7ad31",
                    "order_id": "1",
                    "checkout_session_id": "e71f7ed8-3f4e-4c50-92c4-1a9123a1d10f"
                  },
                  "next_action": null,
                  "on_behalf_of": null,
                  "payment_details": {
                    "customer_reference": null,
                    "order_reference": "prod_T0I6p9oStNEZOz"
                  },
                  "payment_method": "pm_1SKfMRBFhLKsY9xGr35M4LeI",
                  "payment_method_configuration_details": null,
                  "payment_method_options": {
                    "card": {
                      "installments": null,
                      "mandate_options": null,
                      "network": null,
                      "request_three_d_secure": "automatic"
                    }
                  },
                  "payment_method_types": ["card"],
                  "processing": null,
                  "receipt_email": null,
                  "review": null,
                  "setup_future_usage": null,
                  "shipping": null,
                  "source": null,
                  "statement_descriptor": null,
                  "statement_descriptor_suffix": null,
                  "status": "succeeded",
                  "transfer_data": null,
                  "transfer_group": null
                }
              },
              "livemode": false,
              "pending_webhooks": 2,
              "request": {
                "id": null,
                "idempotency_key": "5ea19cac-e9dc-448f-898e-9cde1ef089ef"
              },
              "type": "payment_intent.succeeded"
            }
            """;
}
