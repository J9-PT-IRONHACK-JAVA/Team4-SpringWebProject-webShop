package com.ironhack.shopweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Team4SpringWebProjectShopWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(Team4SpringWebProjectShopWebApplication.class, args);
    }

}
