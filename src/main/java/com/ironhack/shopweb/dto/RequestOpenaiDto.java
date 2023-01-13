package com.ironhack.shopweb.dto;

import lombok.Data;

@Data
public class RequestOpenaiDto {
    private String model;
    private String prompt;
    private int max_tokens;
}
