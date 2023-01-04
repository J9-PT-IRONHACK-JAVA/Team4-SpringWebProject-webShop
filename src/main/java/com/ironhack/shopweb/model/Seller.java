package com.ironhack.shopweb.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    private List<Product> productList;

}
