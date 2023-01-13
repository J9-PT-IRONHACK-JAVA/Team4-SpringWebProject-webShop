package com.ironhack.shopweb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.shopweb.dto.ClientDto;
import com.ironhack.shopweb.dto.SellerDto;
import com.ironhack.shopweb.dto.UserDto;
import com.ironhack.shopweb.model.Client;
import com.ironhack.shopweb.model.Seller;
import com.ironhack.shopweb.model.User;
import com.ironhack.shopweb.repository.UserRepository;
import com.ironhack.shopweb.security.*;
import com.ironhack.shopweb.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
//@WebMvcTest(UserControllerTest.class)
//@Import({SecurityConfig.class})
@WithMockUser(username = "admin", password = "admin",roles = {"ADMIN"})
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper om;


    @Test
    public void test_create_general_user() throws Exception {
        var userToCreate = new UserDto("usertest","testpass");

        var userCreated =  new User("usertest","testpass","ROLE_ADMIN");

        when(userService.createUser(userToCreate)).thenReturn(userCreated);

        mockMvc.perform(post("/user")
                        .with(httpBasic("admin","admin"))
                        .header("User-Agent","Test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(userToCreate)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("usertest"));
    }

    @Test
    public void test_create_new_client() throws Exception {
        var clientToCreate = new Client("client",
                "client","ROLE_CLIENT",
                "Cliente Numero 1","Address client Nº 1",
                "mail@client.com","+34545454","ES");

        var clientCreated = new ClientDto("client",
                "client",
                "Cliente Numero 1","Address client Nº 1",
                "mail@client.com","+34545454","ES","Postman");
        when(userService.registerClient(clientToCreate,"Postman")).thenReturn(clientCreated);

        mockMvc.perform(post("/registerclient")
                        .header("User-Agent","Test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(clientToCreate)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").value("client"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").value("Test"));
    }

    @Test
    public void test_create_new_seller() throws Exception {
        var sellerToCreate = new Seller("seller",
                "seller","ROLE_SELLER",
                "Company Name Seller 1","Address Seller Nº 1",
                "mail@client.com","+34545454");

        var sellerCreated = new SellerDto("seller",
                "seller","Company Name Seller 1","Address Seller Nº 1",
                "mail@client.com", "+34545454", List.of(),"");
        when(userService.registerSeller(sellerToCreate,"Postman")).thenReturn(sellerCreated);

        mockMvc.perform(post("/registerseller")
                        .header("User-Agent","Test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(sellerToCreate)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("seller"));
    }



}