package com.ironhack.shopweb.controller;

import com.ironhack.shopweb.dto.ProductDto;
import com.ironhack.shopweb.model.Product;
import com.ironhack.shopweb.service.ProductService;
import com.ironhack.shopweb.service.SellerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    private final ProductService productService;

    @PostMapping("/addproduct")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody @Valid ProductDto productDto){
        return sellerService.addProduct(productDto);
    }



}
