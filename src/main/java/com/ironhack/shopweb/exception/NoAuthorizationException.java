package com.ironhack.shopweb.exception;

public class NoAuthorizationException extends RuntimeException{

    public NoAuthorizationException(String user){
        super("The user '"+user+"' has not authorization.");
    }

}
