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

                // CLIENT ENDPOINTS
                .requestMatchers(HttpMethod.GET,"/client").hasRole("CLIENT")
                .requestMatchers(HttpMethod.PATCH,"/client").hasRole("CLIENT")

                // SELLER ENDPOINTS
                .requestMatchers(HttpMethod.POST,"/seller").hasRole("SELLER")
                .requestMatchers(HttpMethod.PATCH,"/seller").hasRole("SELLER")

                // REGISTER ENDPOINTS - Free access
                .requestMatchers(HttpMethod.POST,"/users/registerseller").permitAll()
                .requestMatchers(HttpMethod.POST,"/users/registerclient").permitAll()

                // ADMIN ENDPOINTS

                //.requestMatchers(HttpMethod.DELETE,"/").hasRole("ADMIN")
                //.requestMatchers(HttpMethod.PATCH,"/").hasRole("ADMIN")
                //.requestMatchers(HttpMethod.POST,"/").hasRole("ADMIN")

                .anyRequest()
                .authenticated()
                .and()
                .userDetailsService(jpaUserDetailService)
                .httpBasic()
                .and()
                .build();
         }



}
