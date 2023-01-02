package com.ironhack.shopweb.demo;


import com.ironhack.shopweb.model.User;
import com.ironhack.shopweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("load-data")
@RequiredArgsConstructor
@Log
public class DataLoader {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @EventListener(ApplicationReadyEvent.class)
    public void loadData(){
        log.info("Loading Data to Database....");


        var user1 = new User("admin",passwordEncoder.encode("admin"),"ROLE_USER,ROLE_ADMIN");
        userRepository.save(user1);


        var user2 = new User("user",passwordEncoder.encode("user"),"ROLE_USER");
        userRepository.save(user2);



        log.info("Final Loading Data...");

    }

}
