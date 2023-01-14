package com.ironhack.shopweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;

    private BigDecimal amount;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "products_carts",
            joinColumns = @JoinColumn(name="cart_id"),
            inverseJoinColumns = @JoinColumn (name = "product_id"))
    @JsonIgnore
    private List<Product> productList;

}
