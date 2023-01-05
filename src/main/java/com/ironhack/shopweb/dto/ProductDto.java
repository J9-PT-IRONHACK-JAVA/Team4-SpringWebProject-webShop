package com.ironhack.shopweb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.shopweb.model.Product;
import com.ironhack.shopweb.model.Seller;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductDto {

    //private Long id;

    @NotBlank(message = "Name cannot be blank.")
    private String name;

    @NotBlank(message = "Description cannot be blank.")
    private String description;

    private String ean;

    @Positive(message = "Price must be positive.")
    private BigDecimal price;

    @Positive(message = "Stock must be positive.")
    private Long stock;

    @JsonIgnore
    private Seller seller;

    //TODO: Ver si queremos que el producto muestre la lista de CARTS y ORDERS a la que pertenece
    //private List<Cart> cartList;

    //private List<Order> orderList;

    public static ProductDto fromProduct(Product product){
        var productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setEan(product.getEan());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        productDto.setSeller(product.getSeller());
        return productDto;
    }
}
