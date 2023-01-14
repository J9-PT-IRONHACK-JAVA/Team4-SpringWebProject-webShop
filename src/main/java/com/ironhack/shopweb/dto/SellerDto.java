package com.ironhack.shopweb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.shopweb.model.Product;
import com.ironhack.shopweb.model.Seller;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerDto {

    @NotBlank (message = "Username cannot be blank")
    private String username;

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

    @JsonIgnore
    private List<Product> productList;

    private String platform;

    public static SellerDto fromSeller(Seller seller) {
        var sellerDto = new SellerDto();
        sellerDto.setUsername(seller.getUsername());
        sellerDto.setPassword(seller.getPassword());
        sellerDto.setCompanyName(seller.getCompanyName());
        sellerDto.setAddress(seller.getAddress());
        sellerDto.setEmail(seller.getEmail());
        sellerDto.setPhone(seller.getPhone());
        sellerDto.setPlatform(seller.getPlatform());
        return sellerDto;
    }
}
