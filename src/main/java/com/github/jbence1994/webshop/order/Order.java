package com.github.jbence1994.webshop.order;

import com.github.jbence1994.webshop.cart.Cart;
import com.github.jbence1994.webshop.checkout.CheckoutPrice;
import com.github.jbence1994.webshop.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratedColumn;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer;

    private BigDecimal totalPrice;

    private BigDecimal discountAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private int loyaltyPoints;

    @Column(insertable = false, updatable = false)
    @GeneratedColumn("created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public boolean isPlacedBy(String customerEmail) {
        return customerEmail.equals(customer.getEmail());
    }

    public static Order from(
            User customer,
            CheckoutPrice checkoutPrice,
            Cart cart
    ) {
        var order = new Order();

        order.setCustomer(customer);
        order.setTotalPrice(checkoutPrice.getTotalPrice());
        order.setDiscountAmount(checkoutPrice.getDiscountAmount());
        order.setStatus(OrderStatus.CREATED);

        var items = cart.mapCartItemsToOrderItems();

        items.forEach(item -> {
                    item.setOrder(order);
                    order.items.add(item);
                }
        );

        return order;
    }

    public boolean isEligibleForFreeShipping(BigDecimal threshold) {
        return totalPrice.compareTo(threshold) >= 0;
    }
}
