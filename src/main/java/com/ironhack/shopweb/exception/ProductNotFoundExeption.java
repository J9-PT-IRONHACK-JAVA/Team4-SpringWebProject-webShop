package com.ironhack.shopweb.exception;

public class ProductNotFoundExeption extends RuntimeException{

    public ProductNotFoundExeption(Long id){
        super("The product with ID "+id+", was not found.");
    }

}
