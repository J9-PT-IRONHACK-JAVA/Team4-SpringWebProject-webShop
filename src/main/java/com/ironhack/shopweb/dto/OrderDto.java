package com.ironhack.shopweb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.shopweb.model.Client;
import com.ironhack.shopweb.model.Order;
import com.ironhack.shopweb.model.Product;
import com.ironhack.shopweb.model.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDto {

    private String id;

    private Status orderStatus;

    private BigDecimal amount;

    @JsonIgnore
    private Client client;

    private List<Product> productList;

    public static OrderDto fromOrder(Order order){
        var orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setAmount(order.getAmount());
        orderDto.setClient(order.getClient());
        orderDto.setProductList(order.getProductList());
        return orderDto;
    }

}
