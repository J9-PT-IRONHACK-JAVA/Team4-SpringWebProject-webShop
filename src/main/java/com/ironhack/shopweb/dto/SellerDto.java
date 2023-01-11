package com.ironhack.shopweb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.shopweb.model.Product;
import com.ironhack.shopweb.model.Seller;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class SellerDto {

    @NotBlank (message = "Username cannot be blank")
    private String username;

    // TODO: ver como solucionar que NO MUESTRE EL PASSWORD, pero que nos permita crearlo
    // Lo que podemos hacer es de crearlo pasandole un SELLER y no un SELLERDTO
    @JsonIgnore
    @NotBlank (message = "Password cannot be blank")
    private String password;

    @NotBlank (message = "Company Name cannot be blank")
    private String companyName;

    @NotBlank (message = "Addres cannot be blank")
    private String address;

    @NotBlank (message = "Email cannot be blank")
    private String email;

    @NotBlank (message = "Phone cannot be blank")
    private String phone;

    //TODO: Ver si queremos que este la lista de productos en el vendedor
    @JsonIgnore
    private List<Product> productList;

    public static SellerDto fromSeller(Seller seller) {
        var sellerDto = new SellerDto();
        sellerDto.setUsername(seller.getUsername());
        sellerDto.setPassword(seller.getPassword());
        sellerDto.setCompanyName(seller.getCompanyName());
        sellerDto.setAddress(seller.getAddress());
        sellerDto.setEmail(seller.getEmail());
        sellerDto.setPhone(seller.getPhone());
        return sellerDto;
    }
}
