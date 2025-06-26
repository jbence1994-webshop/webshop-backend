package com.github.jbence1994.webshop.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddressDto {
    private String addressLine;
    private String municipality;
    private String province;
    private String postalCode;
    private String country;
}
