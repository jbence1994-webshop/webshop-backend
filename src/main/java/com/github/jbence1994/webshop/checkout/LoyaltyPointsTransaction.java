package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.order.Order;
import com.github.jbence1994.webshop.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "loyalty_points_transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoyaltyPointsTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private LoyaltyPointsAction action;

    @Column(insertable = false, updatable = false)
    @GeneratedColumn("created_at")
    private LocalDateTime createdAt;
}
