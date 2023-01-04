package com.ironhack.shopweb.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    private final JpaUserDetailService jpaUserDetailService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests()
                //TODO: Todas las rutas y la autorizacion segun el tipo de USUARIO
                //.requestMatchers("/public").permitAll()
                //.requestMatchers(HttpMethod.GET,"/quotes").permitAll()
                .requestMatchers(HttpMethod.POST,"/seller").hasRole("SELLER")
                //.requestMatchers(HttpMethod.DELETE,"/quotes/**").hasRole("ADMIN")
                //.requestMatchers(HttpMethod.PATCH,"/quotes/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/users").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .userDetailsService(jpaUserDetailService)
                .httpBasic()
                .and()
                .build();
         }



}
