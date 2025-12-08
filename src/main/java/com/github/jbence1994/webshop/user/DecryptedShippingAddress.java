package com.github.jbence1994.webshop.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DecryptedShippingAddress {
    private Long userId;
    private DecryptedUser user;
    private String addressLine;
    private String municipality;
    private String province;
    private String postalCode;
    private String country;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
