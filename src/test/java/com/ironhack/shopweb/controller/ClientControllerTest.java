package com.ironhack.shopweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.shopweb.dto.CartDto;
import com.ironhack.shopweb.dto.ClientDto;
import com.ironhack.shopweb.dto.OrderDto;
import com.ironhack.shopweb.dto.ProductDto;
import com.ironhack.shopweb.model.Product;
import com.ironhack.shopweb.service.ClientService;
import com.ironhack.shopweb.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
//@WithMockUser(username = "client", password = "client",roles = {"CLIENT"})
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClientService clientService;

    @MockBean
    ProductService productService;

    @Autowired
    ObjectMapper om;

    @Test
    public void test_find_all_products() throws Exception {
        var listOfProductsDto = List.of(
                new ProductDto(),
                new ProductDto(),
                new ProductDto(),
                new ProductDto()
        );
        when(productService.findAllProducts()).thenReturn(listOfProductsDto);

        mockMvc.perform(get("/client/allproducts")
                        .with(httpBasic("client","client")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*",hasSize(4)));
    }

    @Test
    public void test_find_product_by_id() throws Exception {
        Optional<ProductDto> productDto = Optional.of(new ProductDto(
        ));

        productDto.get().setName("Name Optional Product");
        productDto.get().setStock(4L);

        when(productService.viewProduct(1L)).thenReturn(productDto);

        mockMvc.perform(get("/client/viewproduct/{id}",1L)
                    .with(httpBasic("client","client")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Name Optional Product"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stock").value(4));
    }

    @Test
    public void test_update_client_info() throws Exception {

        var clientDto = new ClientDto("newclient","password","Name Of Client","Addres of Client",
                                        "client@client.com","+344555454","ES","Test");

        var finalClientDto = new ClientDto("newclient","password","Name Of Client","Addres of Client",
                "client@client.com","+344555454","ES","Test");;

        var newEmail = "newmail@client.com";
        var newPhone = "+66554433";
        finalClientDto.setPhone(newPhone);
        finalClientDto.setEmail(newEmail);

        when(clientService.updateData(Optional.empty(), Optional.empty(),Optional.of("newmail@client.com"),
                Optional.of("+66554433"), Optional.empty(), Optional.empty())).thenReturn(finalClientDto);

        mockMvc.perform(patch("/client/update")
                        .param("email",newEmail)
                        .param("phone",newPhone)
                        .with(httpBasic("client","client")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value(newPhone))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(newEmail))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("newclient"));
    }

    @Test
    public void test_add_product_to_cart() throws Exception {

        var cartReturned = new CartDto();
        var product1 = new Product();
        product1.setId(1L);
        product1.setPrice(BigDecimal.valueOf(12.12));

        cartReturned.setProductList(List.of(product1));
        cartReturned.setAmount(BigDecimal.valueOf(12.12));

        when(clientService.addToCart(1L)).thenReturn(cartReturned);

        mockMvc.perform(put("/client/addtocart/{productId}",1L)
                    .with(httpBasic("client","client")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(12.12))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productList[0].id").value(1L));
    }

    @Test
    public void test_checkout() throws Exception {

        var order = new OrderDto();
        var product1 = new Product();
        product1.setId(1L);
        product1.setPrice(BigDecimal.valueOf(12.12));

        order.setProductList(List.of(product1));
        order.setAmount(BigDecimal.valueOf(12.12));

        when(clientService.checkout()).thenReturn(order);

        mockMvc.perform(put("/client/checkout")
                        .with(httpBasic("client","client")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(12.12))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productList[0].id").value(1L));
    }

    @Test
    public void test_view_cart() throws Exception {

        var cartReturned = new CartDto();
        var product1 = new Product();
        product1.setId(1L);
        product1.setPrice(BigDecimal.valueOf(10));
        var product2 = new Product();
        product2.setId(2L);
        product2.setPrice(BigDecimal.valueOf(5));
        var product3 = new Product();
        product3.setId(3L);
        product3.setPrice(BigDecimal.valueOf(4));

        cartReturned.setProductList(List.of(product1,product2,product3));
        cartReturned.setAmount(BigDecimal.valueOf(19));

        when(clientService.viewcart()).thenReturn(cartReturned);

        mockMvc.perform(get("/client/cart")
                        .with(httpBasic("client","client")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(19))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productList[1].price").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productList[*]",hasSize(3)));
    }

    @Test
    public void test_find_all_orders() throws Exception {

        var orders = List.of(
                                        new OrderDto(),
                                        new OrderDto(),
                                        new OrderDto(),
                                        new OrderDto()
                                        );

        when(clientService.findAllOrders()).thenReturn(orders);

        mockMvc.perform(get("/client/orders")
                        .with(httpBasic("client","client")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]",hasSize(4)));
    }

    @Test
    public void test_no_auth() throws Exception {
        var listOfProductsDto = List.of(
                new ProductDto(),
                new ProductDto(),
                new ProductDto(),
                new ProductDto()
        );
        when(productService.findAllProducts()).thenReturn(listOfProductsDto);

        mockMvc.perform(get("/client/allproducts")
                    .with(httpBasic("client","badPassword")))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

}