package com.ironhack.shopweb.service;


import com.ironhack.shopweb.dto.ProductDto;
import com.ironhack.shopweb.exception.ProductNotFoundException;
import com.ironhack.shopweb.model.Client;
import com.ironhack.shopweb.model.Product;
import com.ironhack.shopweb.proxy.TranslateProxy;
import com.ironhack.shopweb.repository.ProductRepository;
import com.ironhack.shopweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final TranslateService translateService;

    public List<ProductDto> findAllProducts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var client = (Client) userRepository.findByUsername(authentication.getName()).get();
        var listOfProductsDto = new ArrayList<ProductDto>();
        for(Product product : productRepository.findAll()){
            product.setName(translateService.translate(product.getName(),client.getLanguage()));
            product.setDescription(translateService.translate(product.getDescription(),client.getLanguage()));
            listOfProductsDto.add(ProductDto.fromProduct(product));
        }
        return listOfProductsDto;
    }


    public Optional<ProductDto> viewProduct(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var client = (Client) userRepository.findByUsername(authentication.getName()).get();
        var product = productRepository.findById(id);
        if(product.isPresent()) {
            product.get().setName(translateService.translate(product.get().getName(),client.getLanguage()));
            product.get().setDescription(translateService.translate(product.get().getDescription(),client.getLanguage()));
            return Optional.of(ProductDto.fromProduct(product.get()));
        }
        else throw new ProductNotFoundException(id);
    }

}

