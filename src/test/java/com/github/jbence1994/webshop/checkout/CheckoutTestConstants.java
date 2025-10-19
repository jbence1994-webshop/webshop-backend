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
              "id" : "evt_3SJvslBFhLKsY9xG1U4hscA8",
              "object" : "event",
              "api_version" : "2025-08-27.basil",
              "created" : 1760877523,
              "data" : {
                "object" : {
                  "id" : "ch_3SJvslBFhLKsY9xG1VPOYDpo",
                  "object" : "charge",
                  "amount" : 5798,
                  "amount_captured" : 5798,
                  "amount_refunded" : 0,
                  "application" : null,
                  "application_fee" : null,
                  "application_fee_amount" : null,
                  "balance_transaction" : null,
                  "billing_details" : {
                    "address" : {
                      "city" : null,
                      "country" : "HU",
                      "line1" : null,
                      "line2" : null,
                      "postal_code" : null,
                      "state" : null
                    },
                    "email" : "juhasz.bence.zsolt@gmail.com",
                    "name" : "Juhász Bence Zsolt",
                    "phone" : null,
                    "tax_id" : null
                  },
                  "calculated_statement_descriptor" : "Stripe",
                  "captured" : true,
                  "created" : 1760877523,
                  "currency" : "usd",
                  "customer" : null,
                  "description" : null,
                  "destination" : null,
                  "dispute" : null,
                  "disputed" : false,
                  "failure_balance_transaction" : null,
                  "failure_code" : null,
                  "failure_message" : null,
                  "fraud_details" : { },
                  "livemode" : false,
                  "metadata" : {
                    "cart_id" : "16b44976-e93b-48aa-84d5-cdf70df367e4",
                    "order_id" : "6",
                    "checkout_session_id" : "37eef5b2-c4f1-4284-80be-c3f82ff89316"
                  },
                  "on_behalf_of" : null,
                  "order" : null,
                  "outcome" : {
                    "advice_code" : null,
                    "network_advice_code" : null,
                    "network_decline_code" : null,
                    "network_status" : "approved_by_network",
                    "reason" : null,
                    "risk_level" : "normal",
                    "risk_score" : 22,
                    "seller_message" : "Payment complete.",
                    "type" : "authorized"
                  },
                  "paid" : true,
                  "payment_intent" : "pi_3SJvslBFhLKsY9xG1bEYiv6Z",
                  "payment_method" : "pm_1SJvskBFhLKsY9xGNVb8HIJm",
                  "payment_method_details" : {
                    "card" : {
                      "amount_authorized" : 5798,
                      "authorization_code" : "036106",
                      "brand" : "visa",
                      "checks" : {
                        "address_line1_check" : null,
                        "address_postal_code_check" : null,
                        "cvc_check" : null
                      },
                      "country" : "US",
                      "exp_month" : 10,
                      "exp_year" : 2030,
                      "extended_authorization" : {
                        "status" : "disabled"
                      },
                      "fingerprint" : "YpEydrd5pDmvCglQ",
                      "funding" : "credit",
                      "incremental_authorization" : {
                        "status" : "unavailable"
                      },
                      "installments" : null,
                      "last4" : "4242",
                      "mandate" : null,
                      "multicapture" : {
                        "status" : "unavailable"
                      },
                      "network" : "visa",
                      "network_token" : {
                        "used" : false
                      },
                      "network_transaction_id" : "891126912110011",
                      "overcapture" : {
                        "maximum_amount_capturable" : 5798,
                        "status" : "unavailable"
                      },
                      "regulated_status" : "unregulated",
                      "three_d_secure" : null,
                      "wallet" : {
                        "dynamic_last4" : null,
                        "link" : { },
                        "type" : "link"
                      }
                    },
                    "type" : "card"
                  },
                  "radar_options" : { },
                  "receipt_email" : null,
                  "receipt_number" : null,
                  "receipt_url" : "https://pay.stripe.com/receipts/payment/CAcaFwoVYWNjdF8xUzRHa01CRmhMS3NZOXhHKNS308cGMgYrMzrw5AI6LBboWGasyTX4VXArnF4ZLYnZqfBufCzpsIQcjv6mhY2ci_YkyGJFoyam-sBJ",
                  "refunded" : false,
                  "review" : null,
                  "shipping" : null,
                  "source" : null,
                  "source_transfer" : null,
                  "statement_descriptor" : null,
                  "statement_descriptor_suffix" : null,
                  "status" : "succeeded",
                  "transfer_data" : null,
                  "transfer_group" : null
                }
              },
              "livemode" : false,
              "pending_webhooks" : 2,
              "request" : {
                "id" : null,
                "idempotency_key" : "5667b83a-e669-41d8-bb14-dcfe6700f99e"
              },
              "type" : "charge.succeeded"
            }
            """;
}
