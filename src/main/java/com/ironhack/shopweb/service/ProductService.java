package com.ironhack.shopweb.service;


import com.ironhack.shopweb.dto.ProductDto;
import com.ironhack.shopweb.exception.ProductNotFoundException;
import com.ironhack.shopweb.model.Product;
import com.ironhack.shopweb.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> findAllProducts() {
        var listOfProductsDto = new ArrayList<ProductDto>();
        for(Product product : productRepository.findAll()){
            listOfProductsDto.add(ProductDto.fromProduct(product));
        }
        return listOfProductsDto;
    }

    public Optional<ProductDto> viewProduct(Long id) {
        var product = productRepository.findById(id);
        if(product.isPresent()) return Optional.of(ProductDto.fromProduct(product.get()));
        else throw new ProductNotFoundException(id);
    }
}

