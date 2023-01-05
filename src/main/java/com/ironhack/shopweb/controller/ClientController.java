package com.ironhack.shopweb.controller;

import com.ironhack.shopweb.dto.ClientDto;
import com.ironhack.shopweb.dto.ProductDto;
import com.ironhack.shopweb.service.ClientService;
import com.ironhack.shopweb.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {


    private final ClientService clientService;

    private final ProductService productService;

    @GetMapping("/allproducts")
    public List<ProductDto> findAllProducts(){
        return productService.findAllProducts();
    }

    @GetMapping("/viewproduct/{id}")
    public Optional<ProductDto> viewProduct(@PathVariable Long id){
        return productService.viewProduct(id);
    }


    @PatchMapping("/update")
    public ClientDto updateData(@RequestParam Optional<String> name,
                                @RequestParam Optional<String> address,
                                @RequestParam Optional<String> email,
                                @RequestParam Optional<String> phone,
                                @RequestParam Optional<String> password
                                ){
        return clientService.updateData(name,address,email,phone,password);
    }
}
