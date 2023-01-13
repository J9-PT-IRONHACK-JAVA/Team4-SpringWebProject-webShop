package com.ironhack.shopweb.service;

import com.ironhack.shopweb.model.*;
import com.ironhack.shopweb.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CartService cartService;

    public Order generateOrder(Client client, Cart cart) {
        var order = new Order();
        order.setId(generateOrderId(client));
        order.setOrderStatus(Status.IN_PROCESS);
        order.setClient(client);
        order.setAmount(cart.getAmount());
        List<Product> listadeproductos = new ArrayList<>(cart.getProductList());
        order.setProductList(listadeproductos);
        cartService.cleanCart(cart);
        return orderRepository.save(order);
    }

    private String generateOrderId(Client client) {
        var dNow = new Date();
        var ft = new SimpleDateFormat("yyMMddhhmmss");
        var datetime = ft.format(dNow);

        return client.getUsername().substring(0,1).toUpperCase()+
                client.getName().substring(0,1).toUpperCase()+
                datetime;
    }


}
