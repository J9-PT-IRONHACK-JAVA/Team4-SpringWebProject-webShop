package com.ironhack.shopweb.advice;

import com.ironhack.shopweb.exception.ProductNotFoundExeption;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ProductNotFoundExeption.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String getNotFoundHandler(ProductNotFoundExeption ex){
        return ex.getMessage();
    }




    // TODO: Ver la siguiente exceptionHandler
    // Con esta exeption handler, capturamos la exepcion al validar (el @Valid del gameDTO)
    // Mapeamos los errores, crea un hashmap con el atrbuto y el error que lanza el valid

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(WebExchangeBindException ex) {
        var errors = new HashMap<String, String>();
        ex.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
