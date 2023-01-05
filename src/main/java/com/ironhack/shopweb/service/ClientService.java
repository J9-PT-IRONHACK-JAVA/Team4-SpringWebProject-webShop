package com.ironhack.shopweb.service;

import com.ironhack.shopweb.dto.ClientDto;
import com.ironhack.shopweb.model.Client;
import com.ironhack.shopweb.model.Seller;
import com.ironhack.shopweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public ClientDto updateData(Optional<String> name, Optional<String> address, Optional<String> email, Optional<String> phone, Optional<String> password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // TODO: Obtenemos el USER con el name de autenthication, lo buscamos en la database, y lo casteamos a Seller
        var client = (Client) userRepository.findByUsername(authentication.getName()).get();

        name.ifPresent(client::setName);
        address.ifPresent(client::setAddress);
        email.ifPresent(client::setEmail);
        phone.ifPresent(client::setPhone);
        password.ifPresent(s -> client.setPassword(passwordEncoder.encode(s)));

        //password.ifPresent(client::setPassword);
        return ClientDto.fromClient(userRepository.save(client));
    }
}
