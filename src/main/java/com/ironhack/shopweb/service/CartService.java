package com.ironhack.shopweb.service;

import com.ironhack.shopweb.model.Cart;
import com.ironhack.shopweb.model.Client;
import com.ironhack.shopweb.model.Product;
import com.ironhack.shopweb.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public void createCart(Client client) {
        var cart = new Cart();
        cart.setId(client.getId());
        cart.setAmount(BigDecimal.valueOf(0));
        cart.setClient(client);
        cartRepository.save(cart);
    }

    public Cart updateCart(Cart cart, Product product) {
        cart.setAmount(cart.getAmount().add(product.getPrice()));
        var listOfProducts = cart.getProductList();
        listOfProducts.add(product);
        cartRepository.save(cart);
        return cart;
    }
    public Cart getCart(Client client) {
        return cartRepository.findCartByClient(client);
    }

    public Cart cleanCart(Cart cart) {
        var listOfProducts = cart.getProductList();
        listOfProducts.clear();
        cart.setProductList(listOfProducts);
        cart.setAmount(BigDecimal.valueOf(0));
        cartRepository.save(cart);
        return cart;
    }
}
