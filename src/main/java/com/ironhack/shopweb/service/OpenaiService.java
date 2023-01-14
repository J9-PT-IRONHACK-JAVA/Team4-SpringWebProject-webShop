package com.ironhack.shopweb.service;

import com.ironhack.shopweb.dto.RequestOpenaiDto;
import com.ironhack.shopweb.proxy.OpenAIProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenaiService {
    private final OpenAIProxy openAIProxy;

    public String requestDescription(String name){
        var requestOpenaiDto = new RequestOpenaiDto();
        requestOpenaiDto.setModel("text-davinci-002");
        String question = "Give me some information of " + name + "in two lines";
        requestOpenaiDto.setPrompt(question);
        requestOpenaiDto.setMax_tokens(160);
        var openaiAnswer= openAIProxy.requestDescription(requestOpenaiDto);
        return openaiAnswer.getChoices().get(0).getText();
    }
}
