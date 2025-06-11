package com.github.jbence1994.webshop.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profiles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Profile {

    @Id
    private Long id;

    @OneToOne
    private User user;

    private String username;

    private String password;

    private int loyaltyPoints;

    @Enumerated(EnumType.STRING)
    private ProfileLevel level;
}
