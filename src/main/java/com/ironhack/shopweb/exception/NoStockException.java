package com.ironhack.shopweb.exception;


public class NoStockException extends RuntimeException{

    public NoStockException(String product){
        super("The product '"+product+"' has not stock.");
    }

}
