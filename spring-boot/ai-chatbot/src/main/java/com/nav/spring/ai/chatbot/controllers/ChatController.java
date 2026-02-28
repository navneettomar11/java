package com.nav.spring.ai.chatbot.controllers;

import com.nav.spring.ai.chatbot.services.MultiProviderService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private MultiProviderService multiProviderService;

    @GetMapping("/ask")
    public String multiModelChat(
            @RequestParam(value = "provider", defaultValue = "openai") String provider,
            @RequestParam(value = "model", defaultValue = "gpt-5.2") String model,
            @RequestParam(value = "msg") String message) {
        var chatClient = this.multiProviderService.chatClient(provider);
        return chatClient.prompt()
                .options(ChatOptions.builder().model(model).build())
                .user(message)
                .call()
                .content();
    }
}
