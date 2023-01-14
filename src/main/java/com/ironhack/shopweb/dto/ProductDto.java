package com.ironhack.shopweb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.shopweb.model.Product;
import com.ironhack.shopweb.model.Seller;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductDto {

    @NotBlank(message = "Name cannot be blank.")
    private String name;

    private String description;

    private String ean;

    @Positive(message = "Price must be positive.")
    @Digits(integer=3, fraction=2)
    private BigDecimal price;

    @Positive(message = "Stock must be positive.")
    private Long stock;

    @JsonIgnore
    private Seller seller;

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
