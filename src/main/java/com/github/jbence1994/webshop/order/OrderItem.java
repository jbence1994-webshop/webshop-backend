package com.github.jbence1994.webshop.order;

import com.github.jbence1994.webshop.cart.CartItem;
import com.github.jbence1994.webshop.product.Product;
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

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private BigDecimal unitPrice;

    private Integer quantity;

    private BigDecimal subTotal;

    public static OrderItem from(CartItem cartItem) {
        var orderItem = new OrderItem();

        orderItem.product = cartItem.getProduct();
        orderItem.unitPrice = orderItem.product.getPrice();
        orderItem.quantity = cartItem.getQuantity();
        orderItem.subTotal = orderItem.unitPrice.multiply(BigDecimal.valueOf(orderItem.quantity));

        return orderItem;
    }
}
