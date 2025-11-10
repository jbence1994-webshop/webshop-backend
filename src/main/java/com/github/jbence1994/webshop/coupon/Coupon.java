package com.github.jbence1994.webshop.coupon;

import com.github.jbence1994.webshop.user.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coupons")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coupon {

    @Id
    private String code;

    @Enumerated(EnumType.STRING)
    private DiscountType type;

    private BigDecimal value;

    private String description;

    private LocalDateTime expirationDate;

    @ManyToMany
    @JoinTable(
            name = "profile_coupons",
            joinColumns = @JoinColumn(name = "coupon_code", referencedColumnName = "code"),
            inverseJoinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "user_id")
    )
    private List<Profile> profiles = new ArrayList<>();

    public boolean isExpired() {
        return expirationDate.isBefore(LocalDateTime.now());
    }
}
