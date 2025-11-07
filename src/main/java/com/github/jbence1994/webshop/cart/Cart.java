package com.github.jbence1994.webshop.cart;

import com.github.jbence1994.webshop.order.OrderItem;
import com.github.jbence1994.webshop.product.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(insertable = false, updatable = false)
    @GeneratedColumn("created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public Optional<CartItem> getItem(Long productId) {
        return items.stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();
    }

    public CartItem addItem(Product product) {
        var item = getItem(product.getId())
                .orElseGet(() -> {
                    var newItem = new CartItem(product, 0, this);
                    items.add(newItem);
                    return newItem;
                });

        item.setQuantity(item.getQuantity() + 1);

        return item;
    }

    public void removeItem(Long productId) {
        getItem(productId)
                .ifPresent(cartItem -> {
                    items.remove(cartItem);
                    cartItem.setCart(null);
                });
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public BigDecimal calculateTotal() {
        return items.stream()
                .map(CartItem::calculateSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<OrderItem> mapCartItemsToOrderItems() {
        var orderItems = new ArrayList<OrderItem>();

        items.forEach(item -> orderItems.add(new OrderItem(item)));

        return orderItems;
    }
}
