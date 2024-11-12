package com.example.producer.controller;

import com.example.producer.service.ProduceMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit-producer")
@RequiredArgsConstructor
public class ProduceMessageController {

    private final ProduceMessageService produceMessageService;

    @PostMapping("/produce")
    public String produceMessage(@RequestParam String message) {
        return produceMessageService.produceMessage(message);
    }
}