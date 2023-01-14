package com.ironhack.shopweb.proxy;

import com.ironhack.shopweb.dto.OpenaiDto;
import com.ironhack.shopweb.dto.RequestOpenaiDto;
import com.ironhack.shopweb.dto.TranslateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name ="OpenAI-Chat", url="https://api.openai.com/v1/completions")

public interface OpenAIProxy {
    @PostMapping(headers = {
            "Authorization=Bearer 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",
            "Content-Type=application/json" }
    )
    OpenaiDto requestDescription(
            @RequestBody RequestOpenaiDto requestOpenaiDto);
}
