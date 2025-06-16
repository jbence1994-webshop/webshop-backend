package com.github.jbence1994.webshop.coupon;

import com.github.jbence1994.webshop.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "coupons")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coupon {

    @Id
    private String code;

    private BigDecimal amount;

    private LocalDateTime expirationDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_coupons",
            joinColumns = @JoinColumn(name = "coupon_code", referencedColumnName = "code"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private Set<User> users = new HashSet<>();

    public boolean isExpired() {
        return expirationDate.isBefore(LocalDateTime.now());
    }
}
