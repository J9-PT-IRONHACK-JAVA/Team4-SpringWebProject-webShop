package com.ironhack.shopweb.dto;

import com.ironhack.shopweb.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class SellerDto {

    private String username;

    private String password;

    private String companyName;

    private String address;

    private String email;

    private String phone;

    //TODO: Ver si queremos que este la lista de productos en el vendedor
    private List<Product> productList;

}
