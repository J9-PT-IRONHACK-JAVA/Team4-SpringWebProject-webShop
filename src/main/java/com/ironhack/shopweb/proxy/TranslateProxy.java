package com.ironhack.shopweb.proxy;

import com.ironhack.shopweb.dto.TranslateDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name ="Deepl-translate", url="https://api-free.deepl.com/v2/translate")

public interface TranslateProxy {

    @PostMapping(headers = {
                    "Authorization=DeepL-Auth-Key 52b450b2-6cd3-b0ba-4314-fdc17131d6a7:fx",
                    "Content-Type=application/x-www-form-urlencoded"}
                )
    TranslateDto translate(@RequestParam("text") String text, @RequestParam("target_lang") String targetLang);
}
