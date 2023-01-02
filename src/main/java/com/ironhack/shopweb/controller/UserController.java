package com.ironhack.shopweb.controller;

import com.ironhack.shopweb.dto.UserDto;
import com.ironhack.shopweb.model.User;
import com.ironhack.shopweb.service.UserService;
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

    @PostMapping
    public User createUser (@RequestBody UserDto userDto){

        return userService.createUser(userDto);
    }


}
