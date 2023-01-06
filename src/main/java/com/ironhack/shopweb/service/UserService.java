package com.ironhack.shopweb.service;

import com.ironhack.shopweb.dto.ClientDto;
import com.ironhack.shopweb.dto.SellerDto;
import com.ironhack.shopweb.dto.UserDto;
import com.ironhack.shopweb.model.Cart;
import com.ironhack.shopweb.model.Client;
import com.ironhack.shopweb.model.Seller;
import com.ironhack.shopweb.model.User;
import com.ironhack.shopweb.repository.CartRepository;
import com.ironhack.shopweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final CartService cartService;



    public User createUser(UserDto userDto){
        var user = new User();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUsername(userDto.getUsername());
        user.setRoles("ROLE_USER");
        return userRepository.save(user);
    }

    public SellerDto registerSeller(Seller seller) {
        seller.setPassword(passwordEncoder.encode(seller.getPassword()));
        seller.setRoles("ROLE_SELLER");
        return SellerDto.fromSeller(userRepository.save(seller));
    }

    public ClientDto registerClient(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client.setRoles("ROLE_USER");
        var user = userRepository.save(client);
        // Cuando creamos el usuario, creamos el carrito
        cartService.createCart(client);
        return ClientDto.fromClient(user);
    }


}
