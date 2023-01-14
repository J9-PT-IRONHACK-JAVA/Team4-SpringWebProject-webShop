package com.ironhack.shopweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.shopweb.dto.ProductDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@ToString(exclude = {"cartList","cartList"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String ean;

    private BigDecimal price;

    private Long stock;

    @ManyToOne
    @JoinColumn (name="seller_id")
    @JsonIgnore
    private Seller seller;

    @ManyToMany(mappedBy = "productList")
    @JsonIgnore
    private List<Cart> cartList;

    @ManyToMany(mappedBy = "productList")
    @JsonIgnore
    private List<Order> orderList;

    public Product(String name, String ean, String description,BigDecimal price, Long stock) {
        this.name = name;
        this.ean = ean;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Product(Long id, String name, String description, String ean, BigDecimal price, Long stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ean = ean;
        this.price = price;
        this.stock = stock;
    }

    public static Product fromDto(ProductDto productDto){
        var product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setEan(productDto.getEan());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setSeller(productDto.getSeller());
        return product;
    }
}
