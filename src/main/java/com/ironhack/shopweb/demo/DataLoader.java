package com.ironhack.shopweb.demo;


import com.ironhack.shopweb.model.Client;
import com.ironhack.shopweb.model.Seller;
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


        var user2 = new Seller("seller", passwordEncoder.encode("seller"),
                "ROLE_SELLER","COMPANY NAME","Carrer dels Filadors 22 Barcelona",
                "mail@mail.com","+3423234234");
        userRepository.save(user2);

        var user3 = new Client("client", passwordEncoder.encode("client"),
                "ROLE_CLIENT","Nombre de Cliente","Direccion Cliente",
                "email@email.com","+3433423423","ES");
        userRepository.save(user3);


        log.info("Final Loading Data...");

    }

}
