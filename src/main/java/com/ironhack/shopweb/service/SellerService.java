package com.ironhack.shopweb.service;

import com.ironhack.shopweb.dto.ProductDto;
import com.ironhack.shopweb.model.Product;
import com.ironhack.shopweb.model.Seller;
import com.ironhack.shopweb.repository.ProductRepository;
import com.ironhack.shopweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerService {


    private final UserRepository userRepository;

    private final ProductRepository productRepository;


    public Product addProduct(ProductDto productDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // TODO: Obtenemos el USER con el name de autenthication, lo buscamos en la database, y lo casteamos a Seller
        productDto.setSeller((Seller) userRepository.findByUsername(authentication.getName()).get());
        return productRepository.save(Product.fromDto(productDto));

    }
}
