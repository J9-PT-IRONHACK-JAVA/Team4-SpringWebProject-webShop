package com.ironhack.shopweb.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(Long id){
        super("The product with ID "+id+", was not found.");
    }

}
