package com.ironhack.shopweb.repository;

import com.ironhack.shopweb.model.Cart;
import com.ironhack.shopweb.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    Cart findCartByClient(Client client);
}
