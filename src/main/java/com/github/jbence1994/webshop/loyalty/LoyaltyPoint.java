package com.github.jbence1994.webshop.loyalty;

import com.github.jbence1994.webshop.order.Order;
import com.github.jbence1994.webshop.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(insertable = false, updatable = false)
    @GeneratedColumn("created_at")
    private LocalDateTime createdAt;

    private LocalDateTime expirationDate;

    public LoyaltyPoint(int amount, Order order) {
        this.amount = amount;
        this.user = order.getCustomer();
        this.order = order;
        this.expirationDate = LocalDateTime.now().plusDays(365);
    }
}
