package com.ironhack.shopweb.model;


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


    @OneToMany (mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orderList;

    @OneToOne (mappedBy = "client",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Cart cart ;

}
