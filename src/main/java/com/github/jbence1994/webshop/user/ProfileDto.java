package com.github.jbence1994.webshop.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ProfileDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private int loyaltyPoints;
    private AddressDto address;
}
