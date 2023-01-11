package com.ironhack.shopweb.service;

import com.ironhack.shopweb.dto.CartDto;
import com.ironhack.shopweb.dto.ClientDto;
import com.ironhack.shopweb.dto.OrderDto;
import com.ironhack.shopweb.exception.NoStockException;
import com.ironhack.shopweb.exception.ProductNotFoundException;
import com.ironhack.shopweb.model.*;
import com.ironhack.shopweb.repository.ProductRepository;
import com.ironhack.shopweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final OrderService orderService;

    private final TranslateService translateService;


    public ClientDto updateData(Optional<String> name,
                                Optional<String> address,
                                Optional<String> email,
                                Optional<String> phone,
                                Optional<String> password,
                                Optional<String> language) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var client = (Client) userRepository.findByUsername(authentication.getName()).get();

        name.ifPresent(client::setName);
        address.ifPresent(client::setAddress);
        email.ifPresent(client::setEmail);
        phone.ifPresent(client::setPhone);
        language.ifPresent(client::setLanguage);
        password.ifPresent(s -> client.setPassword(passwordEncoder.encode(s)));

        return ClientDto.fromClient(userRepository.save(client));
    }

    public CartDto addToCart(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var client = (Client) userRepository.findByUsername(authentication.getName()).get();
        //Obtenemos el producto  -> Puede NO EXISTIR
        var product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException(id));
        if(product.getStock()>0){
            Cart cart = cartService.getCart(client);
            cart = cartService.updateCart(cart, product);
            product.setStock(product.getStock()-1);
            productRepository.save(product);
            return translateCart(client, cart);
        }else{
            throw new NoStockException(product.getName());
        }
    }


    public CartDto viewcart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var client = (Client) userRepository.findByUsername(authentication.getName()).get();
        Cart cart = cartService.getCart(client);
        return translateCart(client, cart);
    }

    private CartDto translateCart(Client client, Cart cart) {
        if(!client.getLanguage().equals("EN")) {
            for (Product productTranslated : cart.getProductList()) {
                productTranslated.setDescription(translateService.translate(productTranslated.getDescription(),client.getLanguage()));
                productTranslated.setName(translateService.translate(productTranslated.getName(),client.getLanguage()));
            }
        }
        return CartDto.fromCart(cart);
    }

    public OrderDto checkout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var client = (Client) userRepository.findByUsername(authentication.getName()).get();
        var cart = cartService.getCart(client);
        var order = orderService.generateOrder(client, cart);
        if(!client.getLanguage().equals("EN")){
            for (Product productTranslated : order.getProductList()) {
                productTranslated.setDescription(translateService.translate(productTranslated.getDescription(),client.getLanguage()));
                productTranslated.setName(translateService.translate(productTranslated.getName(),client.getLanguage()));
            }
        }
        return OrderDto.fromOrder(order);
    }


    public List<OrderDto> findAllOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var client = (Client) userRepository.findByUsername(authentication.getName()).get();
        var orderListDto = new ArrayList<OrderDto>();
        for(Order order : client.getOrderList()){
            orderListDto.add(OrderDto.fromOrder(order));
        }
        if(!client.getLanguage().equals("EN")) {
            for (OrderDto orderDto : orderListDto) {
                for (Product productTranslated : orderDto.getProductList()) {
                    productTranslated.setDescription(translateService.translate(productTranslated.getDescription(), client.getLanguage()));
                    productTranslated.setName(translateService.translate(productTranslated.getName(), client.getLanguage()));
                }
            }
        }
        return orderListDto;
    }
}
