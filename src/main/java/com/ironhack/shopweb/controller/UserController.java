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

import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser (@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @PostMapping(value = "/registerseller")
    @ResponseStatus(HttpStatus.CREATED)
    public SellerDto registerSeller(@RequestBody @Valid Seller seller,
                                    @RequestHeader("user-agent") String platform){
        return userService.registerSeller(seller, platform);
    }

    @PostMapping(value = "/registerclient")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto registerClient(@RequestBody Client client,
                                    @RequestHeader("user-agent") String platform){
        return userService.registerClient(client, platform);
    }

    @GetMapping("/headers")
    public String getHeaders(@RequestHeader Map<String,String> headers){
        var response = new String();
        for (String key : headers.keySet()){
            response = response + "Header Name: "+key+" - Header Value: "+headers.get(key)+"+\n";
        }
        return response;
    }

}

