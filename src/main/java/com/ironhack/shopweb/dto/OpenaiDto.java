package com.ironhack.shopweb.dto;

import lombok.Data;

import java.util.List;

@Data
public class OpenaiDto {
    private List<DescriptionOpenaiDto> choices;
}
