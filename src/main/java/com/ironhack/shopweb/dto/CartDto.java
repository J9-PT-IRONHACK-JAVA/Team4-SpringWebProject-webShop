package com.ironhack.shopweb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.shopweb.model.Cart;
import com.ironhack.shopweb.model.Client;
import com.ironhack.shopweb.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class CartDto {

    private Long id;

    private BigDecimal amount;

    @JsonIgnore
    private Client client;

    private List<Product> productList;

    public static CartDto fromCart(Cart cart){
        var cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setAmount(cart.getAmount());
        cartDto.setClient(cart.getClient());
        cartDto.setProductList(cart.getProductList());
        return cartDto;
    }

}
