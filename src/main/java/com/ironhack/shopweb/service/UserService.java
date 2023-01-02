package com.ironhack.shopweb.service;

import com.ironhack.shopweb.dto.UserDto;
import com.ironhack.shopweb.model.User;
import com.ironhack.shopweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;



    public User createUser(UserDto userDto){
        var user = new User();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUsername(userDto.getUsername());
        user.setRoles("ROLE_USER");
        return userRepository.save(user);
    }

}
