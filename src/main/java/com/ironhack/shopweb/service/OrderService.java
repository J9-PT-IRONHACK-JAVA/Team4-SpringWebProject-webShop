package com.ironhack.shopweb.service;

import com.ironhack.shopweb.model.Cart;
import com.ironhack.shopweb.model.Client;
import com.ironhack.shopweb.model.Order;
import com.ironhack.shopweb.model.Status;
import com.ironhack.shopweb.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CartService cartService;

    public Order generateOrder(Client client, Cart cart) {
        var order = new Order();
        order.setClient(client);
        order.setAmount(cart.getAmount());
        order.setProductList(cart.getProductList());
        order.setOrderStatus(Status.IN_PROCESS);
        //cartService.cleanCart(cart);
        return orderRepository.save(order);
    }


}
