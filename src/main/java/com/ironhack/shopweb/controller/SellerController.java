package com.ironhack.shopweb.controller;

import com.ironhack.shopweb.dto.ClientDto;
import com.ironhack.shopweb.dto.ProductDto;
import com.ironhack.shopweb.dto.SellerDto;
import com.ironhack.shopweb.model.Product;
import com.ironhack.shopweb.service.ProductService;
import com.ironhack.shopweb.service.SellerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    private final ProductService productService;


    @PostMapping("/addproduct")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto addProduct(@RequestBody @Valid ProductDto productDto){
        return sellerService.addProduct(productDto);
    }

    @PatchMapping("/updateproduct/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProductDto updateProduct(@PathVariable Long id,
                                @RequestParam Optional<String> name,
                                @RequestParam Optional<String> description,
                                @RequestParam Optional<String> ean,
                                @RequestParam Optional<BigDecimal> price,
                                @RequestParam Optional<Long> stock
    ){
        return sellerService.updateProduct(id,name,description,ean,price,stock);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteProduct(@PathVariable Long id){
        return sellerService.deleteProduct(id);
    }

}
