package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DecryptedUser {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String avatarFileName;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private DecryptedBillingAddress billingAddress;
    private DecryptedShippingAddress shippingAddress;
    private List<Product> favoriteProducts = new ArrayList<>();
}
