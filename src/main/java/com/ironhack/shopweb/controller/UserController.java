package com.ironhack.shopweb.controller;

import com.ironhack.shopweb.dto.ClientDto;
import com.ironhack.shopweb.dto.SellerDto;
import com.ironhack.shopweb.dto.UserDto;
import com.ironhack.shopweb.model.Client;
import com.ironhack.shopweb.model.Seller;
import com.ironhack.shopweb.model.User;
import com.ironhack.shopweb.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User createUser (@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @PostMapping("/registerseller")
    @ResponseStatus(HttpStatus.CREATED)
    public SellerDto registerSeller(@RequestBody @Valid Seller seller){
        return userService.registerSeller(seller);
    }

    @PostMapping("/registerclient")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto registerClient(@RequestBody @Valid Client client){
        return userService.registerClient(client);
    }


    //TODO:
    // Update ORDER STATUS-


}

