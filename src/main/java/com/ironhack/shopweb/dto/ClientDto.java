package com.ironhack.shopweb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.shopweb.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private String username;

    @JsonIgnore
    private String password;

    private String name;

    private String address;

    private String email;

    private String phone;

    private String language;

    private String platform;

    public static ClientDto fromClient(Client client) {
        var clientDto = new ClientDto();
        clientDto.setUsername(client.getUsername());
        clientDto.setPassword(client.getPassword());
        clientDto.setName(client.getName());
        clientDto.setAddress(client.getAddress());
        clientDto.setEmail(client.getEmail());
        clientDto.setPhone(client.getPhone());
        clientDto.setLanguage(client.getLanguage());
        clientDto.setPlatform(client.getPlatform());

        return clientDto;
    }
}
