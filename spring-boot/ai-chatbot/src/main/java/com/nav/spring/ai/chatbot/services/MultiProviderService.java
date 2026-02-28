package com.nav.spring.ai.chatbot.services;

import com.nav.spring.ai.chatbot.constants.AiProvider;
import io.micrometer.observation.Observation;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultiProviderService {

    @Autowired
    private OpenAiApi openAiApi;

    @Autowired
    private OpenAiChatModel openAiChatModel;

    @Autowired
    private OpenAiChatModel baseChatModel;

    public ChatClient chatClient(String provider) {
        if(provider == null || provider.trim().isEmpty()) {
            throw new RuntimeException("");
        }
        AiProvider aiProvider = AiProvider.getInstance(provider);
        return switch (aiProvider){
            case OLLAMA -> {
                    var ollamAiApi = openAiApi.mutate()
                            .baseUrl("")
                            .apiKey("").build();

                    yield ChatClient.create(baseChatModel.mutate()
                            .openAiApi(ollamAiApi)
                            .build());

                }
            case OPENAI -> {
                yield ChatClient.create(openAiChatModel.mutate().build());
            }
            default -> throw new IllegalStateException("Unexpected value: " + provider);
        };
    }
}
