package com.ironhack.shopweb.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = {"orderList", "cart"})
public class Client extends User{

    private String name;

    private String address;

    private String email;

    private String phone;

    private String language;

    @OneToMany (mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Order> orderList;

    @OneToOne (mappedBy = "client",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private Cart cart ;

    public Client(String username, String password, String roles, String name, String address, String email, String phone, String language) {
        super(username, password, roles);
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.language = language;
    }
}
