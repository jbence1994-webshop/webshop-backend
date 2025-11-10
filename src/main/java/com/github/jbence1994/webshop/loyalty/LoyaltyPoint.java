package com.github.jbence1994.webshop.loyalty;

import com.github.jbence1994.webshop.order.Order;
import com.github.jbence1994.webshop.user.Profile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratedColumn;

import java.time.LocalDateTime;

@Entity
@Table(name = "loyalty_points")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoyaltyPoint {

    @Id
    private Long profileId;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    @MapsId
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer amount;

    @Column(insertable = false, updatable = false)
    @GeneratedColumn("created_at")
    private LocalDateTime createdAt;

    private LocalDateTime expirationDate;

    public LoyaltyPoint(int amount, Order order) {
        this.profile = order.getCustomer().getProfile();
        this.order = order;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
        this.expirationDate = LocalDateTime.now().plusDays(365);
    }
}
