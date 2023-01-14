package com.ironhack.shopweb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.shopweb.model.Product;
import com.ironhack.shopweb.model.Seller;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class ProductDto {
    //private Long id;

    @NotBlank(message = "Name cannot be blank.")
    private String name;

    //@NotBlank(message = "Description cannot be blank.")
    private String description;

    private String ean;

    @Positive(message = "Price must be positive.")
    //@NotBlank(message = "Price cannot be blank.")
    @Digits(integer=3, fraction=2)
    private BigDecimal price;

    @Positive(message = "Stock must be positive.")
    //@NotBlank(message = "Stock cannot be blank.")
    private Long stock;

    @JsonIgnore
    private Seller seller;

    public ProductDto(String name, String ean, BigDecimal price, Long stock) {
        this.name = name;
        this.ean = ean;
        this.price = price;
        this.stock = stock;
    }

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
