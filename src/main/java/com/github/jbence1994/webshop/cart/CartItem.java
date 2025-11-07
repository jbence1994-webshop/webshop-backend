package com.github.jbence1994.webshop.cart;

import com.github.jbence1994.webshop.product.Product;
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

import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    public static CartItem from(Product product, Integer quantity, Cart cart) {
        var cartItem = new CartItem();

        cartItem.product = product;
        cartItem.quantity = quantity;
        cartItem.cart = cart;

        return cartItem;
    }

    public BigDecimal calculateSubTotal() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
