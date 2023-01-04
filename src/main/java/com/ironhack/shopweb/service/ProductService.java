package com.ironhack.shopweb.service;


import com.ironhack.shopweb.model.Product;
import com.ironhack.shopweb.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> viewProduct(Long id) {
        return productRepository.findById(id);
    }
}

