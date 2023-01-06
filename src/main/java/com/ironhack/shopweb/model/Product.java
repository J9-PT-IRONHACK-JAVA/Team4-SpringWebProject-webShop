package com.ironhack.shopweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.shopweb.dto.ProductDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
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
