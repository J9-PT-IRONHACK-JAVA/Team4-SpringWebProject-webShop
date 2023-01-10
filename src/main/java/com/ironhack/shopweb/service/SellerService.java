package com.ironhack.shopweb.service;

import com.ironhack.shopweb.dto.ProductDto;
import com.ironhack.shopweb.dto.SellerDto;
import com.ironhack.shopweb.exception.NoAuthorizationException;
import com.ironhack.shopweb.exception.ProductNotFoundException;
import com.ironhack.shopweb.model.Product;
import com.ironhack.shopweb.model.Seller;
import com.ironhack.shopweb.repository.ProductRepository;
import com.ironhack.shopweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerService {


    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private final PasswordEncoder passwordEncoder;



    public ProductDto addProduct(ProductDto productDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        productDto.setSeller((Seller) userRepository.findByUsername(authentication.getName()).get());
        var product = productRepository.save(Product.fromDto(productDto));
        return ProductDto.fromProduct(product);

    }

    public ProductDto updateProduct(Long id, Optional<String> name, Optional<String> description, Optional<String> ean, Optional<BigDecimal> price, Optional<Long> stock) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = authentication.getName();
        var product = productRepository.findById(id);
        if(product.isPresent()){
            if(product.get().getSeller().getUsername().equals(user)) {
                name.ifPresent(s -> product.get().setName(s));
                description.ifPresent(s -> product.get().setDescription(s));
                ean.ifPresent(s -> product.get().setEan(s));
                price.ifPresent(s -> product.get().setPrice(s));
                stock.ifPresent(s -> product.get().setStock(s));
                return ProductDto.fromProduct(productRepository.save(product.get()));
            }else{
                throw new NoAuthorizationException(user);
            }
        }else{
            throw new ProductNotFoundException(id);
        }
    }

    public String deleteProduct(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = authentication.getName();
        var product = productRepository.findById(id);
        if(product.isPresent()){
            if(product.get().getSeller().getUsername().equals(user)) {
                productRepository.delete(product.get());
                return "Product '"+product.get().getName()+"' deleted.";
            }else{
                return "Seller has not authorization for delete product id "+product.get().getId();
            }
        }else{
            throw new ProductNotFoundException(id);
        }
    }
}
