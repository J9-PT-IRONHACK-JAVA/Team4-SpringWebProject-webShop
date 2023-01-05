package com.ironhack.shopweb.controller;

import com.ironhack.shopweb.dto.SellerDto;
import com.ironhack.shopweb.dto.UserDto;
import com.ironhack.shopweb.model.User;
import com.ironhack.shopweb.service.SellerService;
import com.ironhack.shopweb.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final SellerService sellerService;

    @PostMapping
    public User createUser (@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @PostMapping("/registerseller")
    public SellerDto registerSeller(@RequestBody @Valid SellerDto sellerDto){
        return sellerService.registerSeller(sellerDto);
    }


}
