package com.ironhack.shopweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.shopweb.dto.SellerDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = "productList")
public class Seller extends User{

    private String companyName;

    private String address;

    private String email;

    private String phone;

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Product> productList;

    public Seller(String username, String password, String roles, String companyName, String address, String email, String phone) {
        super(username, password, roles);
        this.companyName = companyName;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public static Seller fromDto(SellerDto sellerDto){
        var seller = new Seller();
        seller.setUsername(sellerDto.getUsername());
        seller.setPassword(sellerDto.getPassword());
        seller.setRoles("ROLE_SELLER");
        seller.setCompanyName(sellerDto.getCompanyName());
        seller.setAddress(sellerDto.getAddress());
        seller.setEmail(sellerDto.getEmail());
        seller.setPhone(sellerDto.getPhone());
        return seller;
    }
}
