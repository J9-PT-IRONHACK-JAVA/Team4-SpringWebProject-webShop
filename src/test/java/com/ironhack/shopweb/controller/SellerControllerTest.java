package com.ironhack.shopweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.shopweb.dto.ProductDto;
import com.ironhack.shopweb.model.Product;
import com.ironhack.shopweb.service.SellerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WithMockUser(username="seller", roles="SELLER", password="SELLER")
@AutoConfigureMockMvc
public class SellerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    SellerService sellerService;

    @Autowired
    ObjectMapper om;

    @Test
    public void add_product_test() throws Exception {
        var productToCreate = new ProductDto("apples","98989898", BigDecimal.valueOf(30.99), Long.parseLong("40"));

        var productCreated =  new Product("apples","98989898", "GPT description", BigDecimal.valueOf(30.99), Long.parseLong("40"));

        when(sellerService.addProduct(productToCreate)).thenReturn(ProductDto.fromProduct(productCreated));

        mockMvc.perform(post("/seller/addproduct")
                        .with(httpBasic("seller","SELLER"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(productToCreate)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("apples"));
    }

    @Test
    public void update_product_test() throws Exception {
        //var productToCreate = new ProductDto("apples","98989898", BigDecimal.valueOf(30.99), Long.parseLong("40"));
        var productCreated =  new Product(1L, "apples", "Las apples estan ricas", "98989898",BigDecimal.valueOf(30.99), Long.parseLong("40"));
        var testProduct = new Product(1L, "apples","Las apples estan riquisimas", "98989898", BigDecimal.valueOf(30.99), Long.parseLong("40"));
        var testId = 1L;

        when(sellerService.updateProduct(testId, Optional.empty(), Optional.of(testProduct.getDescription()), Optional.empty(), Optional.empty(), Optional.empty())).thenReturn(ProductDto.fromProduct(testProduct));

        mockMvc.perform(patch("/seller/updateproduct/{id}", testId)
                        .with(httpBasic("seller","SELLER"))
                        .param("description", testProduct.getDescription()))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Las apples estan riquisimas"));
    }

    @Test
    public void delete_product_test() throws Exception {
        var productCreated =  new Product("apples","Las apples estan ricas", "98989898", BigDecimal.valueOf(30.99), Long.parseLong("40"));
        var productId = 1L;
        when(sellerService.deleteProduct(productId)).thenReturn("Product '"+productCreated.getName()+"' deleted.");

        mockMvc.perform(delete("/seller/delete/{id}", productId)
                        .with(httpBasic("seller","SELLER")))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("Product '" + productCreated.getName() + "' deleted.")));
    }
}
