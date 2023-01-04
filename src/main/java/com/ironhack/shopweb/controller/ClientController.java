package com.ironhack.shopweb.controller;

import com.ironhack.shopweb.exception.ProductNotFoundExeption;
import com.ironhack.shopweb.model.Product;
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
    public List<Product> findAllProducts(){
        return productService.findAllProducts();
    }

    // TODO: Retorna un Product? o un ProductDTO?
    @GetMapping("/viewproduct/{id}")
    public Optional<Product> viewProduct(@PathVariable Long id){
        var product = productService.viewProduct(id);
        if(product.isPresent()) return product;
        else throw new ProductNotFoundExeption(id);
    }



}
