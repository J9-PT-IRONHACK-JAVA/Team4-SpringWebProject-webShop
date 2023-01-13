package com.ironhack.shopweb.service;

import com.ironhack.shopweb.proxy.TranslateProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranslateService {

    private final TranslateProxy translateProxy;

    public String translate(String text, String language){
        var toTranslate= translateProxy.translate(text,language);
        return toTranslate.getTranslations().get(0).getText();
    }




}
