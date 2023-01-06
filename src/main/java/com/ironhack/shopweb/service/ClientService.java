package com.ironhack.shopweb.service;

import com.ironhack.shopweb.dto.CartDto;
import com.ironhack.shopweb.dto.ClientDto;
import com.ironhack.shopweb.dto.OrderDto;
import com.ironhack.shopweb.exception.NoStockException;
import com.ironhack.shopweb.exception.ProductNotFoundException;
import com.ironhack.shopweb.model.*;
import com.ironhack.shopweb.repository.CartRepository;
import com.ironhack.shopweb.repository.ProductRepository;
import com.ironhack.shopweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final OrderService orderService;


    public ClientDto updateData(Optional<String> name, Optional<String> address, Optional<String> email, Optional<String> phone, Optional<String> password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // TODO: Obtenemos el USER con el name de autenthication, lo buscamos en la database, y lo casteamos a Seller
        var client = (Client) userRepository.findByUsername(authentication.getName()).get();

        name.ifPresent(client::setName);
        address.ifPresent(client::setAddress);
        email.ifPresent(client::setEmail);
        phone.ifPresent(client::setPhone);
        password.ifPresent(s -> client.setPassword(passwordEncoder.encode(s)));

        //password.ifPresent(client::setPassword);
        return ClientDto.fromClient(userRepository.save(client));
    }

    public CartDto addToCart(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // TODO: Obtenemos el USER con el name de autenthication, lo buscamos en la database, y lo casteamos a Seller
        var client = (Client) userRepository.findByUsername(authentication.getName()).get();
        //Obtenemos el producto  -> Puede NO EXISTIR
        var product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException(id));
        if(product.getStock()>0){
            Cart cart = cartService.getCart(client);
            cart = cartService.updateCart(cart, product);
            product.setStock(product.getStock()-1);
            productRepository.save(product);
            return CartDto.fromCart(cart);
        }else{
            throw new NoStockException(product.getName());
        }
    }


    public CartDto viewcart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var client = (Client) userRepository.findByUsername(authentication.getName()).get();
        Cart cart = cartService.getCart(client);
        return CartDto.fromCart(cart);
    }

    public OrderDto checkout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var client = (Client) userRepository.findByUsername(authentication.getName()).get();
        Cart cart = cartService.getCart(client);
        return OrderDto.fromOrder(orderService.generateOrder(client, cart));
    }




}
