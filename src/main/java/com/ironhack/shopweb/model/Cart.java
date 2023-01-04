package com.ironhack.shopweb.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isActive;

    private BigDecimal amount;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "products_carts",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn (name = "product_cart_id"))
    private List<Product> productList;

}
